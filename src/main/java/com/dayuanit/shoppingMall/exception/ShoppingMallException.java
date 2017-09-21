package com.dayuanit.shoppingMall.exception;

public class ShoppingMallException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ShoppingMallException() {
		
	}
	
	public ShoppingMallException(String msg) {
		super(msg);
	}
}
