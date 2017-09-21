package com.dayuanit.shoppingMall.controller;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.dayuanit.shoppingMall.daomain.User;
import com.dayuanit.shoppingMall.exception.ShoppingMallException;
import com.dayuanit.shoppingMall.service.UserService;
import com.dayuanit.shoppingMall.vo.UserVO;

@Controller
@RequestMapping("/user")
public class UserInforController {
	
	@Autowired
	private UserService userService;
	
	Logger log = LoggerFactory.getLogger(UserInforController.class);
	
	@RequestMapping("/toUserInfor")
	public String toUserInfor (int registId, HttpServletRequest req) {
		
		User user = userService.getUser(registId);
		req.setAttribute("user", user);
		
		return "mygrxx";
	}
	
	@RequestMapping("/modifyUserInfor")
	public String modifyUserInfor (int userId, UserVO userVo) {
		try {
			userService.modifyUserInfor(userId, userVo.getUserPhone(), userVo.getUserSex(), userVo.getUserBirthday());
			
		} catch(ShoppingMallException sme) {
			log.error("修改失败{}", sme.getMessage(), sme);
			sme.printStackTrace();
		} catch(Exception e) {
			log.error("系统异常{}", e.getMessage(), e);
			e.printStackTrace();
			return "系统异常，请联系客服";
		}
		return "mygrxx" ;
	}
}
