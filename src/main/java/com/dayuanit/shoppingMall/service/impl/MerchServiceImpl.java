package com.dayuanit.shoppingMall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dayuanit.shoppingMall.daomain.Merch;
import com.dayuanit.shoppingMall.mapper.MerchMapper;
import com.dayuanit.shoppingMall.service.MerchService;

@Service
public class MerchServiceImpl implements MerchService{
	
	@Autowired
	private MerchMapper merchMapper;
	
	@Transactional(rollbackFor=Exception.class)
	public List<Merch> listMerch(int CategoryId) {
		List<Merch> listMarch = merchMapper.getMerchsByCategoryId(CategoryId);
		return listMarch;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public Merch getMerch(int merchId) {
		Merch merch = merchMapper.getMerchById(merchId);
		return merch;
	}
}
