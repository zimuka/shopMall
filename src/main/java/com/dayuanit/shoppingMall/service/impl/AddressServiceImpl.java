package com.dayuanit.shoppingMall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayuanit.shoppingMall.daomain.Address;
import com.dayuanit.shoppingMall.dto.AddressDTO;
import com.dayuanit.shoppingMall.exception.ShoppingMallException;
import com.dayuanit.shoppingMall.mapper.AddressMapper;
import com.dayuanit.shoppingMall.service.AddressService;
import com.dayuanit.shoppingMall.vo.AddressVO;

@Service
public class AddressServiceImpl implements AddressService{
	
	@Autowired
	private AddressMapper addressMapper;
	
	@Override
	public void add(AddressVO vo, int registId) {
		Address address = new Address();
		address.setRegistId(registId);
		address.setProvinceCode(vo.getProvinceCode());
		address.setCityCode(vo.getCityCode());
		address.setAreaCode(vo.getAreaCode());
		address.setProvinceName(vo.getProvinceName());
		address.setCityName(vo.getCityName());
		address.setAreaName(vo.getAreaName());
		address.setPhone(vo.getPhone());
		address.setDetailAddress(vo.getDetailAddress());
		address.setRealName(vo.getRealName());
		address.setDefaultAddress(vo.getDefaultAddress());
		
		int rows = addressMapper.addAddress(address);
		if (1 != rows) {
			throw new ShoppingMallException("增加地址失败");
		}
	}

	@Override
	public List<AddressDTO> listAddress(int registId) {
		List<Address> listAddress = addressMapper.lsitAddressByRegistId(registId);
		//定义一个arrayList集合，将转换的数据存放在此集合中(定义集合时，确定集合的长度（arraylist底层是数组）)
		List<AddressDTO> dtos = new ArrayList<AddressDTO>(listAddress.size());
		for (Address address : listAddress) {
			AddressDTO dto = new AddressDTO();
			dtos.add(dto);
			
			dto.setProvinceCode(address.getProvinceCode());
			dto.setCityCode(address.getCityCode());
			dto.setAreaCode(address.getAreaCode());
			dto.setProvinceName(address.getProvinceName());
			dto.setCityName(address.getCityName());
			dto.setAreaName(address.getAreaName());
			dto.setPhone(address.getPhone());
			dto.setDetailAddress(address.getDetailAddress());
			dto.setRealName(address.getRealName());
			dto.setDefaultAddress(address.getDefaultAddress());
			dto.setId(address.getId());
		}
		return dtos;
	}

	@Override
	public void modifyAddress(AddressVO vo, int registId) {
		
		Address address = addressMapper.getAddressByAddressId(vo.getAddressId());
		
		if (null == address) {
			throw new ShoppingMallException("地址不存在");
		}
		
		if (address.getRegistId().intValue() != registId) {
			throw new ShoppingMallException("无权修改");
		}
		
		//Address address = new Address();
		address.setProvinceCode(vo.getProvinceCode());
		address.setCityCode(vo.getCityCode());
		address.setAreaCode(vo.getAreaCode());
		address.setProvinceName(vo.getProvinceName());
		address.setCityName(vo.getCityName());
		address.setAreaName(vo.getAreaName());
		address.setPhone(vo.getPhone());
		address.setRealName(vo.getRealName());
		address.setDetailAddress(vo.getDetailAddress());
		address.setDefaultAddress(vo.getDefaultAddress());
		address.setRegistId(registId);
		
		int rows = addressMapper.modifyAddress(address);
		if (1 != rows) {
			throw new ShoppingMallException("修改地址失败");
		}
	}

	@Override
	public void delAddress(int addressId, int registId) {
		Address address = addressMapper.getAddressByAddressId(addressId);
		if (null == address) {
			throw new ShoppingMallException("地址不存在");
		}
		
		if (address.getRegistId().intValue() != registId) {
			throw new ShoppingMallException("无权删除");
		}
		
		int rows = addressMapper.deleteAddress(addressId);
		if (1 != rows) {
			throw new ShoppingMallException("删除失败");
		}
	}

}
