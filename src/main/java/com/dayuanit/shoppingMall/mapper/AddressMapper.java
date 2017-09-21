package com.dayuanit.shoppingMall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dayuanit.shoppingMall.daomain.Address;

public interface AddressMapper {
	
	int addAddress(Address address);
	
	List<Address> lsitAddressByRegistId(@Param("registId") Integer registId);
	
	int modifyAddress(Address address);
	
	Address getAddressByAddressId(@Param("addressId") Integer addressId);
	
	int deleteAddress(@Param("addressId") Integer addressId);
}
