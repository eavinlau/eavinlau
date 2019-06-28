package com.eavinlau.fw.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
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

public class VedioDownload {


	public static void main(String[] args) {
		String m3y8URL="https://valipl-vip.cp31.ott.cibntv.net/677359E258B397172AF1D5C59/03000900005C56EAD45EBDD38E657A053E21F4-BC63-4C5D-816B-3411BBB933E8-1-114.m3u8?ccode=0502&duration=259&expire=18000&psid=5a1f161f0a36e6dfc81046c17fda1752&ups_client_netip=de425d12&ups_ts=1561702492&ups_userid=84228117&utid=ygQJFfib9UICAd5CXRIaJv9j&vid=XNDA0NjUxNjEwMA&vkey=A97dfcb93c518e8de0264c3bb3d850a30&sm=1&operate_type=1&bc=2";
		String vedioFileName="D:/1/一颗小葱-青花瓷";
		String vedioSuffix=".mp4";
		URL url=null;
		try {
			url = new URL(m3y8URL);
			InputStream is = getInputStream (url);
			// 读取响应
			if(is!=null) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				String line=null;
				byte[] buf = new byte[1024]; 
				int i=0;
				List<InputStream> list = new ArrayList<InputStream>();
				Enumeration<InputStream> en =null;
				while ((line = reader.readLine()) != null) {
					line = new String(line.getBytes(), "utf-8");
					System.out.println(line);
					if(line.startsWith("https")) {
						i++;
//						FileUtils.copyURLToFile(new URL(line), new File(vedioFileName+i+vedioSuffix));
						list.add(new URL(line).openStream());
						en = Collections.enumeration(list);
//						InputStream tsis = getInputStream(new URL(line));
//						FileUtils.copyInputStreamToFile(tsis, new File(vedioFileName+i+vedioSuffix));
//						int bytesRead;  
//						while ((bytesRead = tsis.read(buf)) != -1) {
//							fos.write(buf);
//						}
					}
				}
				SequenceInputStream sis = new SequenceInputStream(en);
				FileUtils.copyInputStreamToFile(sis, new File("D:/1/一颗小葱-青花瓷.mp4"));
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
