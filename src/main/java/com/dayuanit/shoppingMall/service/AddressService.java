package com.dayuanit.shoppingMall.service;

import java.util.List;

import com.dayuanit.shoppingMall.dto.AddressDTO;
import com.dayuanit.shoppingMall.vo.AddressVO;

public interface AddressService {
	
	void add(AddressVO vo, int registId);
	
	List<AddressDTO> listAddress(int registId);
	
	void modifyAddress(AddressVO vo, int registId);
	
	void delAddress(int addressId, int registId);
}
