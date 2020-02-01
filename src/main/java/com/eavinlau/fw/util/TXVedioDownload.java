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
		String vedioFileName="D:/eavinlau/杀不死/杀不死01.mp4";
		String m3y8URL="https%3A%2F%2Fapd-8ef03bc1431239b46c462d8fc034dcc8.v.smtcdns.com%2Fmoviets.tc.qq.com%2FAbuJ1vM8iaHWLsmVjJawD8ZfNo0qYkbd5MsM3q4mEtEA%2FuwMROfz2r5xgoaQXGdGnC2df64_gcR4DH6oNabw6qBdCnk2E%2Fpni8ZcNIlpr1ECs9SRFDgZArfTE7wqiSPtMBsW4DjY2QkZHdr6FF4qz9eTzmbc3KCQ8W1mWzmabQhA7skJk7_pUCG7_wVxqy-MPe95uoNavvr5gmx2P7OqKFOTIC27QtMb2klTZK_Tt58cfKv8hfcPfG-tTnXrzZXwSpagBcVcw%2Fo0024qwqegg.321003.ts.m3u8";
		String baseurl=m3y8URL.substring(0,m3y8URL.lastIndexOf("%2F"))+"%2F";
		URL url=null;
		try {
			url = new URL(m3y8URL.replaceAll("%2F", "/").replaceAll("%3A", ":"));
			System.out.println(url);
			InputStream is = url.openStream();
			// 读取响应
			if(is!=null) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				String line=null;
				List<InputStream> list = new ArrayList<InputStream>();
				Enumeration<InputStream> en =null;
				String newurl="";
				while ((line = reader.readLine()) != null) {
					line = new String(line.getBytes(), "utf-8");
					if(!line.startsWith("#EXT")) {
						newurl=baseurl+line;
						newurl=newurl.replaceAll("%2F", "/").replaceAll("%3A", ":");
						System.out.println(newurl);
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
