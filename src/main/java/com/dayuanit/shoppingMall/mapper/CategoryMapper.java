package com.dayuanit.shoppingMall.mapper;

import java.util.List;

import com.dayuanit.shoppingMall.daomain.Category;

public interface CategoryMapper {
	
	List<Category> getCategorysByStatus();
}
