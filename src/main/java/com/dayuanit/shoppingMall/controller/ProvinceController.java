package com.dayuanit.shoppingMall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dayuanit.shoppingMall.dto.AjaxResultDTO;
import com.dayuanit.shoppingMall.service.ProvinceAndCityService;

@Controller
@RequestMapping("/address")
public class ProvinceController {
	
	@Autowired
	private ProvinceAndCityService provinceAndCityService;
	
	@RequestMapping("/listProvince")
	@ResponseBody
	public AjaxResultDTO listProvince() {
		return AjaxResultDTO.success(provinceAndCityService.listProvince());
	}
	
	@RequestMapping("/listCity")
	@ResponseBody
	public AjaxResultDTO listCity(String provinveCode) {
		return AjaxResultDTO.success(provinceAndCityService.listCity(provinveCode));
	}
	
	@RequestMapping("/listArea")
	@ResponseBody
	public AjaxResultDTO listArea(String cityCode) {
		return AjaxResultDTO.success(provinceAndCityService.listArea(cityCode));
	}
}
