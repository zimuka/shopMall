package com.dayuanit.shoppingMall.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dayuanit.shoppingMall.dto.AddressDTO;
import com.dayuanit.shoppingMall.dto.AjaxResultDTO;
import com.dayuanit.shoppingMall.exception.ShoppingMallException;
import com.dayuanit.shoppingMall.service.AddressService;
import com.dayuanit.shoppingMall.vo.AddressVO;

@Controller
@RequestMapping("/address")
public class AddressController extends BaseController{
	
	@Autowired
	private AddressService addressService;
	
	Logger log = LoggerFactory.getLogger(AddressController.class);
	
	@RequestMapping("/addAddress")
	@ResponseBody
	public AjaxResultDTO addAddress(AddressVO vo, HttpServletRequest req) {
		try {
			addressService.add(vo, getUserById(req));
			return AjaxResultDTO.success();
		} catch (ShoppingMallException sme) {
			log.error("添加地址异常{}", sme.getMessage(), sme);
			return AjaxResultDTO.failed(sme.getMessage());
		} catch (Exception e) {
			log.error("添加地址异常{}", e.getMessage(), e);
			e.printStackTrace();
			return AjaxResultDTO.failed("系统异常，请联系客服");
		}
	}
	
	@RequestMapping("/listAddress")
	@ResponseBody
	public AjaxResultDTO listAddress(HttpServletRequest req) {
		try {
			List<AddressDTO> listAddress = addressService.listAddress(getUserById(req));
			return AjaxResultDTO.success(listAddress);
		} catch (ShoppingMallException sme) {
			log.error("查询异常{}", sme.getMessage(), sme);
			return AjaxResultDTO.failed(sme.getMessage());
		} catch (Exception e) {
			log.error("查询异常{}", e.getMessage(), e);
			return AjaxResultDTO.failed("系统异常，请联系客服");
		}
 	}
	
	@RequestMapping("/modify")
	@ResponseBody
	public AjaxResultDTO modify(AddressVO vo, HttpServletRequest req) {
		try {
			addressService.modifyAddress(vo, getUserById(req));
			return AjaxResultDTO.success();
		} catch (ShoppingMallException sme) {
			log.error("修改异常{}", sme.getMessage(), sme);
			return AjaxResultDTO.failed(sme.getMessage());
		} catch (Exception e) {
			log.error("修改异常{}", e.getMessage(), e);
			return AjaxResultDTO.failed("系统异常，请联系客服，老黑~");
		}
	}
	
	@RequestMapping("/detele")
	@ResponseBody
	public AjaxResultDTO detele (int addressId, HttpServletRequest req) {
		try {
			addressService.delAddress(addressId, getUserById(req));
			return AjaxResultDTO.success();
		} catch (ShoppingMallException sme) {
			log.error("删除地址异常{}", sme.getMessage(), sme);
			return AjaxResultDTO.failed(sme.getMessage());
		} catch (Exception e) {
			log.error("删除地址异常{}", e.getMessage(), e); 
				return AjaxResultDTO.failed("系统异常，请联系客服");
		}
	}
	
	@RequestMapping("toAddress")
	public String toAddress() {
		return "address";
	}
	
	@RequestMapping("/toAddress2")
	public ModelAndView toAddress2(Model  model) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("address");
		return mv;
	}
}
