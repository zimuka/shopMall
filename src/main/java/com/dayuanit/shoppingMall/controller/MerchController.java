package com.dayuanit.shoppingMall.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dayuanit.shoppingMall.daomain.Merch;
import com.dayuanit.shoppingMall.dto.AjaxResultDTO;
import com.dayuanit.shoppingMall.exception.ShoppingMallException;
import com.dayuanit.shoppingMall.service.MerchService;

@Controller
@RequestMapping("/merch")
public class MerchController {
	
	@Autowired
	private MerchService merchService;
	
	Logger log = LoggerFactory.getLogger(MerchController.class);
	
	@RequestMapping("/getMersh")
	@ResponseBody
	public AjaxResultDTO getMersh(int categoryId) {
		try {
			List<Merch> listMerch = merchService.listMerch(categoryId);
			return AjaxResultDTO.success(listMerch);
		} catch(ShoppingMallException sme) {
			log.error("浏览异常{}", sme.getMessage(), sme);
			sme.printStackTrace();
			return AjaxResultDTO.failed("浏览异常");
		} catch(Exception e) {
			log.error("系统异常{}", e.getMessage(), e);
			e.printStackTrace();
			return AjaxResultDTO.failed("系统异常，请联系客服");
		}
	}
	
	@RequestMapping("/toMerchDetail")
	public String toMerchDetail(int merchId, HttpServletRequest req) {
		
		Merch merch = merchService.getMerch(merchId);
		req.setAttribute("merch", merch);
		
		return "commodity_introduce";
	}
	
	@RequestMapping("/toMerchDetail2")
	@ResponseBody
	public AjaxResultDTO toMerchDetail2(int merchId, HttpServletRequest req) {
		Merch merch = merchService.getMerch(merchId);
		req.setAttribute("merch", merch);
		return AjaxResultDTO.success(merch);
	}
	
}
