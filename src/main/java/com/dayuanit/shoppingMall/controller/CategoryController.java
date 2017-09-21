package com.dayuanit.shoppingMall.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dayuanit.shoppingMall.daomain.Category;
import com.dayuanit.shoppingMall.dto.AjaxResultDTO;
import com.dayuanit.shoppingMall.exception.ShoppingMallException;
import com.dayuanit.shoppingMall.service.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	Logger log = LoggerFactory.getLogger(CategoryController.class);
	
	@RequestMapping("/getCategory")
	@ResponseBody
	public AjaxResultDTO getCategory() {
		try {
			List<Category> listCategory = categoryService.getCategorys();
			return AjaxResultDTO.success(listCategory);
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
}
