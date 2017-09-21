package com.dayuanit.shoppingMall.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dayuanit.shoppingMall.daomain.ShopCar;
import com.dayuanit.shoppingMall.dto.AjaxResultDTO;
import com.dayuanit.shoppingMall.exception.ShoppingMallException;
import com.dayuanit.shoppingMall.service.ShopService;
import com.dayuanit.shoppingMall.vo.ShopCarVO;

@Controller
@RequestMapping("/shopCar")
public class ShopCarController extends BaseController{
	
	@Autowired
	private ShopService shopService;
	
	Logger log = LoggerFactory.getLogger(ShopCarController.class);
	
	@RequestMapping("/joinShopCar")
	@ResponseBody
	public AjaxResultDTO joinShopCar(HttpServletRequest req, ShopCarVO shopCarVo) {
		try {
			
			log.info(">>>>>>>开始添加购物车<<<<<<");
			shopService.joinShopCar(getUserById(req), shopCarVo.getMerchId(), shopCarVo.getMerchNum());
			return AjaxResultDTO.success();
		} catch (ShoppingMallException sme) {
			log.error("增加流水失败{}", sme.getMessage(), sme);
			sme.printStackTrace();
			return AjaxResultDTO.failed(sme.getMessage());
		} catch(Exception e) {
			log.error("系统异常{}", e.getMessage(), e);
			e.printStackTrace();
			return AjaxResultDTO.failed("系统异常，请联系客服");
		}
	}
	
	@RequestMapping("/toCart")
	public String toCart() {
		return "cart";
	}
	
	@RequestMapping("/ShopCarDetails")
	@ResponseBody
	public AjaxResultDTO ShopCarDetails(HttpServletRequest req) {
		try {
			List<ShopCar> getShopCars = shopService.getShopCars(getUserById(req));
			return AjaxResultDTO.success(getShopCars);
		} catch (ShoppingMallException sme) {
			log.error("查看失败{}", sme.getMessage(), sme);
			//sme.printStackTrace();
			return AjaxResultDTO.failed(sme.getMessage());
		} catch (Exception e) {
			log.error("系统异常{}", e.getMessage(), e);
			e.printStackTrace();
			return AjaxResultDTO.failed("系统异常，请联系客服");
		}
	}
	
	@RequestMapping("/delectShopDetail")
	@ResponseBody
	public AjaxResultDTO delectShopDetail(HttpServletRequest req, int shopCarId) {
		try {
			shopService.delectShopCar(getUserById(req), shopCarId);
			return AjaxResultDTO.success();
		} catch (ShoppingMallException  sme) {
			log.error("删除失败{}", sme.getMessage(), sme);
			sme.printStackTrace();
			return AjaxResultDTO.failed(sme.getMessage());
		} catch (Exception e) {
			log.error("删除异常{}", e.getMessage(), e);
			e.printStackTrace();
			return AjaxResultDTO.failed("系统异常，请联系客服");
		}
	}
	
	@RequestMapping("/toOrder")
	public ModelAndView toOrder(String orderId) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("orderId", orderId);
		mv.setViewName("order");
		return mv;
	}
	
	@RequestMapping("toAddress")
	public String toAddress() {
		return "address";
	}
	
	@RequestMapping("/toAddress2")
	public ModelAndView toAddress2() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("address");
		return mv;
	}
	
	
}
