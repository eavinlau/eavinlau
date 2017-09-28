package com.eavinlau.fw.util;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5 {

	public static String GetMD5Code(String str) {
		String md5code = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");

			md5code = new BigInteger(1, md.digest(str.getBytes())).toString(16);
			// 如果生成数字未满32位，需要前面补0
			for (int i = 0; i < 32 - md5code.length(); i++) {
				md5code = "0" + md5code;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return md5code;
	}

	public static void main(String[] args) {
		String s = MD5.GetMD5Code("eavinlau");
		System.out.println(s);
	}
}
