package com.dayuanit.shoppingMall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dayuanit.shoppingMall.daomain.Category;
import com.dayuanit.shoppingMall.mapper.CategoryMapper;
import com.dayuanit.shoppingMall.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryMapper categoryMapper;
		
	@Transactional(rollbackFor=Exception.class)
	public List<Category> getCategorys() {
		List<Category> listCategory = categoryMapper.getCategorysByStatus();
		return listCategory;
	}

}
