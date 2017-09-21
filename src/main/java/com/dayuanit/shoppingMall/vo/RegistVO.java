package com.dayuanit.shoppingMall.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class RegistVO {
	
	@NotNull
	@Length(min = 2, max = 5, message="用户名必须2~5个长度")
	private String username;
	
	@NotNull
	@Length(min = 2, max = 5, message="密码必须2~5个长度")
	private String password;
	
	@NotNull
	@Length(min = 2, max = 5, message="密码必须2~5个长度")
	private String confirmPassword;
	
	@NotNull
	@Email(message="邮箱格式不正确")
	private String email;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
