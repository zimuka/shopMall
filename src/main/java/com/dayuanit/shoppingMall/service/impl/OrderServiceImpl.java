package com.dayuanit.shoppingMall.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayuanit.pay.domain.PayOrder;
import com.dayuanit.pay.service.PayService;
import com.dayuanit.shoppingMall.daomain.Address;
import com.dayuanit.shoppingMall.daomain.Merch;
import com.dayuanit.shoppingMall.daomain.Order;
import com.dayuanit.shoppingMall.daomain.OrderDetail;
import com.dayuanit.shoppingMall.dto.BuyMerchDetail;
import com.dayuanit.shoppingMall.dto.MerchOrderInfoDTO;
import com.dayuanit.shoppingMall.dto.MerchOrderInfoDTO.MerchInfo;
import com.dayuanit.shoppingMall.dto.MyOrderDTO;
import com.dayuanit.shoppingMall.dto.MyOrderDTO.OrderMerchs;
import com.dayuanit.shoppingMall.eunms.MerchStautsEnums;
import com.dayuanit.shoppingMall.eunms.OrderFormEunms;
import com.dayuanit.shoppingMall.eunms.OrderStatusEnums;
import com.dayuanit.shoppingMall.eunms.PayTypeEnum;
import com.dayuanit.shoppingMall.exception.ShoppingMallException;
import com.dayuanit.shoppingMall.mapper.AddressMapper;
import com.dayuanit.shoppingMall.mapper.MerchMapper;
import com.dayuanit.shoppingMall.mapper.OrderDetailMapper;
import com.dayuanit.shoppingMall.mapper.OrderMapper;
import com.dayuanit.shoppingMall.service.OrderService;
import com.dayuanit.shoppingMall.utils.DateUtils;
import com.dayuanit.shoppingMall.utils.MoneyUtil;
import com.dayuanit.shoppingMall.utils.PageUtils;
import com.dayuanit.shoppingMall.vo.BuyMerchVO;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderDetailMapper orderDeatilMapper;
	
	@Autowired
	private MerchMapper merchMapper;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private AddressMapper addressMapper;
	
	@Autowired
	private PayService payService;

	@Override
	public List<BuyMerchDetail> createOrderFormShopCar(String buyMsg, int registId) {
		String[] merchs = buyMsg.split("\\$");
		List<BuyMerchDetail> bmds = new ArrayList<BuyMerchDetail>();
		
		for (String merchMsg : merchs) {
			if (StringUtils.isBlank(merchMsg)) {
				continue;
			}
			String[] merchDetails = merchMsg.split("\\-");
			//校验商品
			Merch merch = merchMapper.getMerchById(Integer.valueOf(merchDetails[0]));
			if (null == merch) {
				throw new ShoppingMallException("商品不存在");
			}
			
			if (merch.getMerchStock().intValue() < Integer.valueOf(merchDetails[1])) {
				throw new ShoppingMallException("商品库存不足");
			}
			
			if (merch.getStatus() == 2) {
				throw new ShoppingMallException("商品已下架");
			}
			
			BuyMerchDetail bmd = new BuyMerchDetail();
			bmds.add(bmd);
			bmd.setMerch(merch);
			bmd.setQuantity(Integer.valueOf(merchDetails[1]));
		}
		
		processOrderDetail(bmds, registId);
		return bmds;
	}
	
	private void processOrderDetail(List<BuyMerchDetail> bmds, int registId) {
		//生成订单
		Order order = new Order();
		order.setOrderFrom(2);
		order.setStatus(1);
		order.setRegistId(registId);
		
		//获取总价
		String totalAmount = "0";
		for (BuyMerchDetail bmd : bmds) {
			String amount = MoneyUtil.mul(String.valueOf(bmd.getQuantity()), String.valueOf(bmd.getMerch().getMerchPrice()));
			totalAmount = MoneyUtil.plus(amount, totalAmount);
		}
		
		//将计算的订单总价set到订单amount属性中
		order.setAmount(totalAmount);
		int rows = orderMapper.addOrder(order);
		if (1 != rows) {
			throw new ShoppingMallException("生成订单失败");
		}
		
		for (BuyMerchDetail bmd : bmds) {
			OrderDetail detail = new OrderDetail();
			String amount = MoneyUtil.mul(String.valueOf(bmd.getQuantity()), String.valueOf(bmd.getMerch().getMerchPrice()));
			detail.setAmount(amount);
			detail.setMerchId(bmd.getMerch().getId());
			detail.setMerchName(bmd.getMerch().getMerchName());
			detail.setOrderId(order.getId());
			detail.setQuantity(bmd.getQuantity());
			detail.setUnitPrice(String.valueOf(bmd.getMerch().getMerchPrice()));
			
			rows = orderDeatilMapper.save(detail);
			if (1 != rows) {
				throw new ShoppingMallException("生成订单失败");
			}
		}
	}
	
	
	
	@Override
	public Order createOrderFormShopCar(List<BuyMerchVO> vos, int registId) {
		
		//定义一个ArrayList用来存放遍历出来的订单信息
		List<OrderDetail> goodList = new ArrayList<OrderDetail>(vos.size());
		
		String totalMoney = "0";
		
		//遍历终端传来的所有的商品
		for (BuyMerchVO vo : vos) {
			//通过商品Id查找到对应的商品
			Merch merch = merchMapper.getMerchById(vo.getMerchId());
			//生成订单之前，判断商品状态，是否下架
			if (merch.getStatus() == MerchStautsEnums.DOWN.getK()) {
				throw new ShoppingMallException(String.format("商品%s已下架" + merch.getMerchName()));
			}
			//判断库存是否足够
			if (merch.getMerchStock() < vo.getMerchNum()) {
				throw new ShoppingMallException(String.format("商品%s库存不足" + merch.getMerchName()));
			}
			
			//遍历出商品后，需要将商品信息入库
			OrderDetail od = new OrderDetail();
		
			String merchTotalMoney = MoneyUtil.mul(merch.getMerchPrice(), String.valueOf(vo.getMerchNum()));
			od.setAmount(merchTotalMoney);
			od.setMerchId(merch.getId());
			od.setMerchName(merch.getMerchName());
			od.setUnitPrice(merch.getMerchPrice());
			od.setQuantity(vo.getMerchNum());
			//缺少OrderId
			goodList.add(od);
			
			totalMoney = MoneyUtil.plus(totalMoney, merchTotalMoney);
		}
		
		//生成总订单
		Order order = new Order();
		order.setAmount(totalMoney);
		order.setOrderFrom(OrderFormEunms.CART.getK());
		order.setStatus(OrderStatusEnums.待结算.getK());
		order.setRegistId(registId);
		order.setPayChannel(-1);
		
		int rows = orderMapper.addOrder(order);
		if (1 != rows) {
			throw new ShoppingMallException("生成订单失败");
		}
		
		for (OrderDetail od : goodList) {
			od.setOrderId(order.getId());
			rows = orderDeatilMapper.save(od);
			if (1 != rows) {
				throw new ShoppingMallException("生成订单失败");
			}
		}
		return order;
	}

	@Override
	public MerchOrderInfoDTO loadOrderInfo(int orderId, int registId) {
		Order order = orderMapper.getOrderById(orderId);
		if (order == null) {
			throw new ShoppingMallException("结算信息不存在");
		}
		
		if (registId != order.getRegistId()) {
			throw new ShoppingMallException("订单不属于你，无权修改");
		}
		
		MerchOrderInfoDTO dto = new MerchOrderInfoDTO();
		dto.setAmount(order.getAmount());
		List<OrderDetail> list = orderDeatilMapper.listOrderDetail(orderId);
		List<MerchInfo> merch = new ArrayList<MerchInfo>(list.size());
		
		for (OrderDetail od : list) {
			MerchInfo info = new MerchInfo();
			merch.add(info);
			
			info.setAmount(od.getAmount());
			info.setMerchName(od.getMerchName());
			info.setQuantity(od.getQuantity());
		}
		
		dto.setMerchs(merch);
		return dto;
	}

	@Override
	public Map<String, Object> pay(int orderId, int payChannle, int addressId, int regist) {
		Order order = orderMapper.getOrderById(orderId);
		if (null == order) {
			throw new ShoppingMallException("订单不存在");
		}
		
		if (order.getRegistId() != regist) {
			throw new ShoppingMallException("订单不属于你");
		}
		
		//判断订单状态是否是待支付或者是待结账
		if (order.getStatus() != OrderStatusEnums.待结算.getK() 
				&& order.getStatus() != OrderStatusEnums.待支付.getK()) {
			throw new ShoppingMallException("订单不能处理");
		}
		
		//设置订单失效时间
		Date orderTime = order.getModifyTime();
		//获取一个日历
		Calendar cal = Calendar.getInstance();
		cal.setTime(orderTime);
		//设定30分钟订单过期
		cal.add(Calendar.MINUTE, 30);
		
		Date expDate = cal.getTime();
		//订单失效时间是以modifyTime来当做起始的时间
		if (new Date().after(expDate)) {
			throw new ShoppingMallException("订单失效");
		}
		
		if (order.getStatus() == OrderStatusEnums.待结算.getK()) {
			int rows = orderMapper.changeOrderStatus(OrderStatusEnums.待支付.getK(), regist, orderId);
			if (1 != rows) {
				throw new ShoppingMallException("订单更新失败");
			}
		}
		
		order.setPayChannel(payChannle);
		//根据AddressId得到对应的具体的Address
		Address ad = addressMapper.getAddressByAddressId(addressId);
		String provinceName = ad.getProvinceName();
		String cityName = ad.getCityName();
		String areaName = ad.getAreaName();
		
		order.setProvinceName(provinceName);
		order.setCityName(cityName);
		order.setAreaName(areaName);
		order.setDetailAddress(ad.getDetailAddress());
		order.setRealAame(ad.getRealName());
		order.setPhone(ad.getPhone());
		
		int rows = orderMapper.updateOrderInfo(order);
		if (1 != rows) {
			throw new ShoppingMallException("订单生成失败");
		}
		
		//请求支付系统，获取url地址
		PayOrder payOrder = new PayOrder();
		payOrder.setAmount(order.getAmount());
		payOrder.setBankId(null);
		payOrder.setBizId(String.valueOf(order.getId()));
		payOrder.setDetailMsg("大猿商城");
		payOrder.setUserId(order.getRegistId());
		payOrder.setPayChannel(order.getPayChannel());
		
		Map<String, Object> map = payService.addPayOrder(payOrder);
		return map;
	}
	
	Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Override
	public PageUtils<MyOrderDTO> listEffectivedOrder(int page, Integer registId, Integer status) {
		if (status < 0) {
			status = null;
		}
		
		int total = orderMapper.countOrderNum(registId, status);
		
		PageUtils<MyOrderDTO> pageUtils = new PageUtils<MyOrderDTO>(page, total);
		
		log.info("registId=" + registId + "status=" + status);
		List<Order> listEffectivedOrder = orderMapper.listOrderByRegistIdAndStauts(registId, status, pageUtils.getOffset(), PageUtils.PRE_PAGE_NUM);
		
		List<MyOrderDTO> dtoList = new ArrayList<MyOrderDTO>(listEffectivedOrder.size());
		for (Order order : listEffectivedOrder) {
			MyOrderDTO dto = new MyOrderDTO();
			dtoList.add(dto);
			
			dto.setAmount(order.getAmount());
			dto.setCreateTime(DateUtils.dateToString(order.getCreateTime()));
			dto.setId(order.getId());
			dto.setPayChannel(PayTypeEnum.getPayType(order.getPayChannel()).getDesc());
			dto.setStatus(OrderStatusEnums.getOrderStatus(order.getStatus()).getV());
			dto.setUserRealName(order.getRealName());
			
			List<OrderDetail> listOrderDatail = orderDeatilMapper.listOrderDetail(order.getId());
			List<OrderMerchs> merchsList = new ArrayList<OrderMerchs>(listOrderDatail.size());
			for (OrderDetail od : listOrderDatail) {
				MyOrderDTO.OrderMerchs merch = new MyOrderDTO.OrderMerchs();
				merchsList.add(merch);
				
				merch.setMerchName(od.getMerchName());
				merch.setNum(od.getQuantity());
				merch.setPrice(od.getUnitPrice());
				
				log.info("merchName=" + merch.getMerchName() + "merchNum=" + merch.getNum() + "merchPrice=" + merch.getPrice());
			}
			
			dto.setMerchs(merchsList);
		}
		
		pageUtils.setData(dtoList);
		return pageUtils;
	}
	
}
