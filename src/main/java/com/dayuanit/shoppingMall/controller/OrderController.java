package com.dayuanit.shoppingMall.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.dayuanit.shoppingMall.daomain.Order;
import com.dayuanit.shoppingMall.dto.AjaxResultDTO;
import com.dayuanit.shoppingMall.dto.BuyMerchDetail;
import com.dayuanit.shoppingMall.dto.MerchOrderInfoDTO;
import com.dayuanit.shoppingMall.dto.MyOrderDTO;
import com.dayuanit.shoppingMall.exception.ShoppingMallException;
import com.dayuanit.shoppingMall.service.OrderService;
import com.dayuanit.shoppingMall.utils.PageUtils;
import com.dayuanit.shoppingMall.vo.BuyMerchVO;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;

@Controller
@RequestMapping("/userOrder")
public class OrderController extends BaseController{
	
	private static final Logger log = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;
	
//	@RequestMapping("/toMyOrder")
//	public String toOrder() {
//		return "myorderq";
//	}
	
	@RequestMapping("/toMyOrder")
	public ModelAndView toMyOrder(String orderId) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("orderId", orderId);
		mv.setViewName("myorderq");
		return mv;
	}
	
	@RequestMapping("/createOrder")
	@ResponseBody
	public AjaxResultDTO createOrder(String buyMsg, HttpServletRequest req) {
		try {
			log.info(">>>>>>>>>>订单生成中"+ buyMsg + "<<<<<<<<<<");
			if (StringUtils.isBlank(buyMsg)) {
				return AjaxResultDTO.failed("信息不合法");
			}
			List<BuyMerchDetail> dto = orderService.createOrderFormShopCar(buyMsg, getUserById(req));
			return AjaxResultDTO.success(dto);
		} catch (ShoppingMallException sme) {
			log.error("生成订单异常{}", sme.getMessage(), sme);
			return AjaxResultDTO.failed(sme.getMessage());
		} catch (Exception e) {
			log.error("生成订单异常{}", e.getMessage(), e);
			return AjaxResultDTO.failed("系统异常，请联系客服");
		}
	}
	
	@RequestMapping("/createOrder4Json")
	@ResponseBody
	public AjaxResultDTO createOrder4Json(String buyMsg) {
		log.info("购买商品信息{}", buyMsg);
		try {
			
			List<BuyMerchVO> voList = JSON.parseArray(buyMsg, BuyMerchVO.class);
			return AjaxResultDTO.success();
		} catch (ShoppingMallException sme) {
			log.error("生成订单异常{}", sme.getMessage(), sme);
			sme.printStackTrace();
			return AjaxResultDTO.failed(sme.getMessage());
		} catch (Exception e) {
			log.error("生成订单异常{}", e.getMessage(), e);
			e.printStackTrace();
			return AjaxResultDTO.failed("系统异常，请联系客服。客服热线：10086");
		}
	}
	
	@RequestMapping("/createOrder4JsonBody")
	@ResponseBody
	public AjaxResultDTO createOrder4JsonBody(@RequestBody List<BuyMerchVO> vos, HttpServletRequest req) {
		try {
			log.info(">>>>>" + vos.size());
			for (BuyMerchVO vo : vos) {
				log.info(">>>>>" + vo.getMerchId());
				log.info(">>>>>" + vo.getMerchNum());
			}
			
			Order order = orderService.createOrderFormShopCar(vos, getUserById(req));
			return AjaxResultDTO.success(order);
		} catch (ShoppingMallException sme) {
			log.error("生成订单异常{}", sme.getMessage(), sme);
			sme.printStackTrace();
			return AjaxResultDTO.failed(sme.getMessage());
		} catch (Exception e) {
			log.error("生成订单异常{}", e.getMessage(), e);
			e.printStackTrace();
			return AjaxResultDTO.failed("系统异常，请联系客服。 客服热线：10086");
		}
		
	}
	
	@RequestMapping("/loadOrderInfo")
	@ResponseBody
	public AjaxResultDTO loadOrderInfo(int orderId, HttpServletRequest req) {
		try {
			MerchOrderInfoDTO dto = orderService.loadOrderInfo(orderId, getUserById(req));
			return AjaxResultDTO.success(dto);
		} catch (ShoppingMallException sme) {
			return AjaxResultDTO.failed(sme.getMessage());
		} catch (Exception e) {
			log.error("订单生成失败{}", e.getMessage(),e);
			return AjaxResultDTO.failed("生成失败");
		}
	}
	
	@RequestMapping("/pay")
	@ResponseBody
	public AjaxResultDTO pay (int orderId, int payChannle, int addressId, HttpServletRequest req) {
		log.info("用户确认支付：orderId={},payChannle={},addressId={},regist={}",orderId,payChannle,addressId,getUserById(req));
		
		try {
			Map<String, Object> map = orderService.pay(orderId, payChannle, addressId, getUserById(req));
			return AjaxResultDTO.success(map);
		} catch (ShoppingMallException sme) {
			return AjaxResultDTO.failed(sme.getMessage());
		} catch (Exception e) {
			log.info("订单更新失败{}", e.getMessage(), e);
			return AjaxResultDTO.failed("订单生成失败");
		}
		
	}
	
	@RequestMapping("/loadOrder")
	@ResponseBody
	public AjaxResultDTO loadOrder(@RequestParam(required=false, defaultValue="1") int page, int status, HttpServletRequest req) {
		
		try {
			PageUtils<MyOrderDTO> list = orderService.listEffectivedOrder(page, getUserById(req), status);
			return AjaxResultDTO.success(list);
		} catch(ShoppingMallException sme) {
			return AjaxResultDTO.failed(sme.getMessage());
		} catch(Exception e) {
			log.error("加载订单失败{}", e.getMessage(), e);
			return AjaxResultDTO.failed("加载订单失败");
		}
	}
	
//	public static void main(String[] args) {
//		
//		Jedis jedis = new Jedis("10.10.10.101", 6379);
//		jedis.set("测试用例2", "小黑");
//		log.info(jedis.get("测试用例2"));
//		log.info(jedis.get("test:redis"));
//		
//		JedisPool iedisPool = new JedisPool("10.10.10.101", 6379);
//		jedis = iedisPool.getResource();
//		log.info(jedis.get("test:redis"));
//	}
}
