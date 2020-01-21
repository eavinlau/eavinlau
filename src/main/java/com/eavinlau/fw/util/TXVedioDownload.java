package com.eavinlau.fw.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.SequenceInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class TXVedioDownload {


	public static void main(String[] args) {
		String vedioFileName="D:/eavinlau/忽而今夏/忽而今夏04.mp4";
		String m3y8URL="https%3A%2F%2Fapd-1b409e8818cc1bd30a32cee835da00eb.v.smtcdns.com%2Fvipts.tc.qq.com%2FAv83t-KKJi0Ux85akb4KiDp0beWVdaj2_MsNDgzXv1DE%2FuwMROfz2r5xgoaQXGdGnC2df64_gcR4DH6oNabw6qBdCnk2E%2FOgGWcvobFIHs1MUS4qfQoLG2BHviVd4AUQv5G4Cz8kg9tfSY_t0gK3toRFxowaPSo33vNugZVLeKEKOkjb9F58plwgZFl7NIM3QAi3jrpr5n7IwCHvErV-rWP_veVN23bSFXNCMCD0qNO3A2U9Tq3ZYP7WoG84xDT47HYE7vw-I%2Fr00264m3wrh.321003.ts.m3u8";
		String baseurl="https%3A%2F%2Fapd-1b409e8818cc1bd30a32cee835da00eb.v.smtcdns.com%2Fvipts.tc.qq.com%2FAv83t-KKJi0Ux85akb4KiDp0beWVdaj2_MsNDgzXv1DE%2FuwMROfz2r5xgoaQXGdGnC2df64_gcR4DH6oNabw6qBdCnk2E%2FOgGWcvobFIHs1MUS4qfQoLG2BHviVd4AUQv5G4Cz8kg9tfSY_t0gK3toRFxowaPSo33vNugZVLeKEKOkjb9F58plwgZFl7NIM3QAi3jrpr5n7IwCHvErV-rWP_veVN23bSFXNCMCD0qNO3A2U9Tq3ZYP7WoG84xDT47HYE7vw-I%2F";
		URL url=null;
		try {
			url = new URL(m3y8URL.replaceAll("%2F", "/").replaceAll("%3A", ":"));
			System.out.println(url);
//			if(1==1) {
//				return;
//			}
			InputStream is = getInputStream (url);
			// 读取响应
			if(is!=null) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				String line=null;
//				byte[] buf = new byte[1024]; 
				List<InputStream> list = new ArrayList<InputStream>();
				Enumeration<InputStream> en =null;
				String newurl="";
				while ((line = reader.readLine()) != null) {
					line = new String(line.getBytes(), "utf-8");
					if(!line.startsWith("#EXT")) {
//						FileUtils.copyURLToFile(new URL(line), new File(vedioFileName+i+vedioSuffix));
						newurl=baseurl+line;
						newurl=newurl.replaceAll("%2F", "/").replaceAll("%3A", ":");
//						System.out.println(newurl);
						list.add(new URL(newurl).openStream());
					}
				}
				en = Collections.enumeration(list);
				SequenceInputStream sis = new SequenceInputStream(en);
				FileUtils.copyToFile(sis, new File(vedioFileName));
				System.out.println("完成");
			}else {
				System.err.println("m3p8没获得资源！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static InputStream getInputStream (URL url) {
		HttpURLConnection connection;
		try {
			connection = (HttpURLConnection) url.openConnection();
			connection.connect();
			return connection.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
