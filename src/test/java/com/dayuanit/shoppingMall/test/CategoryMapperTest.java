package com.dayuanit.shoppingMall.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.assertEquals;
import java.util.List;
import com.dayuanit.shoppingMall.daomain.Category;
import com.dayuanit.shoppingMall.mapper.CategoryMapper;


@ContextConfiguration("/spring/spring-app.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CategoryMapperTest {
	
	@Autowired
	private CategoryMapper categoryMapper;
	
	@Test
	@Rollback
	public void testGetCategorysByStatus() {
		List<Category> listCategory = categoryMapper.getCategorysByStatus();
		assertEquals(1, listCategory.size());
	}
}
