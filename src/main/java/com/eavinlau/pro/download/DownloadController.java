package com.eavinlau.pro.download;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.wanghaomiao.xpath.model.JXDocument;

@Controller
@RequestMapping("/download")
public class DownloadController {
	private static Logger logger = Logger.getLogger(DownloadController.class); 
	
	@RequestMapping("/goSearch")
	public String goSearch(Model model,HttpServletRequest request) throws Exception{
		return "download/search";
	}
	//搜索mp4的链接
	@RequestMapping("/search")
	public String search(Model model,HttpServletRequest request) throws Exception{
		String url = request.getParameter("url");
		String xpath="//*/video/@src";
		System.setProperty("webdriver.chrome.driver", "C:/Program Files (x86)/Google/Chrome/Application/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get(url);
		String html = driver.getPageSource();
		logger.info(html);
		JXDocument jxDocument = new JXDocument(html);
		List<Object> rs = jxDocument.sel(xpath);
		String mp4url = "";
		for (Object o:rs){
		    System.out.println(o.toString());
		    mp4url = o.toString();
		    driver.get(mp4url);
		}
		model.addAttribute("mp4url", mp4url);
		return "download/search";
	}
}
