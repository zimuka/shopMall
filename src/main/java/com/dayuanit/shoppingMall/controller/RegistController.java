package com.dayuanit.shoppingMall.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dayuanit.shoppingMall.daomain.Regist;
import com.dayuanit.shoppingMall.dto.AjaxResultDTO;
import com.dayuanit.shoppingMall.exception.ShoppingMallException;
import com.dayuanit.shoppingMall.service.RegistService;
import com.dayuanit.shoppingMall.vo.RegistVO;

@Controller
@RequestMapping("/regist")
public class RegistController extends BaseController{
	
	@Autowired
	public RegistService registService;
	
	Logger log = LoggerFactory.getLogger(RegistController.class);
	
	@RequestMapping("/toRegist")
	public String toRegist() {
		return "regist";
	}
	
	@RequestMapping("/regist")
	@ResponseBody
	public AjaxResultDTO regist(@Validated RegistVO registVO, BindingResult br) {
		try {
			registService.openAccount(registVO.getUsername(), registVO.getPassword(), registVO.getConfirmPassword(), registVO.getEmail());
			log.info("111111111111111111111111111111111111111111111");
			//校验RegistVO中标签的格式问题
			if (br.hasErrors()) {
				String msg = "";
				List<FieldError> errors = br.getFieldErrors();
				for (FieldError fd : errors) {
					msg += fd.getDefaultMessage() + ",";
				}
				return AjaxResultDTO.failed(msg);
			}
			return AjaxResultDTO.success();
			
		} catch (ShoppingMallException sme) {
			log.error("注册失败{}", sme.getMessage(), sme);
			return AjaxResultDTO.failed(sme.getMessage());
		} catch(Exception e) {
			log.error("系统异常{}", e.getMessage(), e);
			e.printStackTrace();
			return AjaxResultDTO.failed("系统异常，请联系客服");
		}
	}
	
	@RequestMapping("/toLogin")
	public String toLogin() {
		return "login";
	}
	
	public static void main(String[] args) {
		Logger log = LoggerFactory.getLogger(RegistController.class);
		log.info(">>>>>>>");
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public AjaxResultDTO login(RegistVO registVO, HttpServletRequest req) {
		try {
			
			log.info("登录中。。。。。。。。。。。。。。。");
			Regist regist = registService.login(registVO.getUsername(), registVO.getPassword());
			req.getSession().setAttribute("regist", regist);
			return AjaxResultDTO.success();
		} catch (ShoppingMallException sme) {
			log.error("登录异常{}", sme.getMessage(), sme);
			return AjaxResultDTO.failed(sme.getMessage());
		} catch(Exception e) {
			log.error("系统异常{}", e.getMessage(), e);
			e.printStackTrace();
			return AjaxResultDTO.failed("系统异常，请联系客服");
		}
	}
	
}
