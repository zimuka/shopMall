package com.dayuanit.shoppingMall.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	public static String dateToString(Date date) {
		return new SimpleDateFormat("yyyy-MM-hh HH:mm:ss").format(date);
	}
}
