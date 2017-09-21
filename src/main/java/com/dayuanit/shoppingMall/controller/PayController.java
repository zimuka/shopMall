package com.dayuanit.shoppingMall.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dayuanit.pay.domain.PayType;
import com.dayuanit.pay.service.PayService;
import com.dayuanit.shoppingMall.dto.AjaxResultDTO;
import com.dayuanit.shoppingMall.exception.ShoppingMallException;

@Controller
@RequestMapping("/pay")
public class PayController {
	
	private static final Logger log = LoggerFactory.getLogger(PayController.class);
	
	@Autowired
	private PayService payService;
	
	@RequestMapping("/getPayChannle")
	@ResponseBody
	public AjaxResultDTO getPayChannel() {
		
		try {
			List<PayType> list = payService.listPayType();
			return AjaxResultDTO.success(list);
		} catch(ShoppingMallException sme) {
			return AjaxResultDTO.failed(sme.getMessage());
		} catch(Exception e) {
			log.error("获取支付渠道失败{}", e.getMessage(), e);
			return AjaxResultDTO.failed(e.getMessage());
		}
	}
}
