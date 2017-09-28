package com.eavinlau.fw.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	private final static String PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	private static SimpleDateFormat sdf = new SimpleDateFormat(PATTERN);
	
	public static String getDate(){
		return sdf.format(new Date());
	}
}
