package com.dayuanit.shoppingMall.service;

import java.util.List;

import com.dayuanit.shoppingMall.daomain.Merch;

public interface MerchService {
	
	Merch getMerch(int merchId);
	
	List<Merch> listMerch(int CategoryId);
}
