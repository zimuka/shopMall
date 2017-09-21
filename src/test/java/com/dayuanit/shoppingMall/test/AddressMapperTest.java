package com.dayuanit.shoppingMall.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.assertEquals;
import com.dayuanit.shoppingMall.daomain.Address;
import com.dayuanit.shoppingMall.mapper.AddressMapper;

@ContextConfiguration("/spring/spring-app.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AddressMapperTest {
	
	@Autowired
	private AddressMapper addressMapper;
	
	private Address address;
	
	@Before
	public void init() {
		address = new Address();
		address.setAreaCode("11111");
		address.setAreaName("xxx县");
		address.setCityCode("10111");
		address.setCityName("xxx市");
		address.setProvinceCode("10001");
		address.setProvinceName("xxx省");
		address.setPhone("123456789");
		address.setDefaultAddress(1);
		address.setDetailAddress("女祭司");
		address.setStatus(1);
		address.setRegistId(2);
		address.setRealName("liulaosan");
	}
	
	@Test
	@Rollback
	public void testAddAddress() {
		int rows = addressMapper.addAddress(address);
		assertEquals(1, rows);
	}
	
	@Test
	@Rollback
	public void testModifyAddress() {
		addressMapper.addAddress(address);
		address.setAreaName("sad");
		address.setRealName("laohei");
		int rows = addressMapper.modifyAddress(address);
		assertEquals(1, rows);
	}
	
	@Test
	@Rollback
	public void testGetAddressByAddressId() {
		addressMapper.addAddress(address);
		Address addressOther = addressMapper.getAddressByAddressId(address.getId());
		assertEquals(addressOther.getId(), address.getId());
	}
	
	@Test
	@Rollback
	public void testDeleteAddress() {
		addressMapper.addAddress(address);
		int rows = addressMapper.deleteAddress(address.getId());
		assertEquals(1, rows);
	}
}
