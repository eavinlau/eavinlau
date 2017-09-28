package com.eavinlau.pro.home.web;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.eavinlau.pro.home.entity.HomeData;
import com.eavinlau.pro.home.service.HomeService;

@Controller
@RequestMapping("/home")
public class HomeController {
	private static Logger logger = Logger.getLogger(HomeController.class); 
	
	@Autowired
	HomeService homeService;
	
	//主页
	@RequestMapping("/main")
	public String main(Model model,HttpServletRequest request){
		logger.info("welcome！！！home");
		List<HomeData> list = homeService.findAllList();
		
		model.addAttribute("homeList", list);
		model.addAttribute("eavinlau", "eavinlau");
		return "home/main";
	}
	
	//模块主页
	@RequestMapping("/typeindex")
	public String typeindex(Model model,HttpServletRequest request){
		String type = request.getParameter("type");
		logger.info("welcome！！！"+type);
		HomeData h = new HomeData();
		h.setType(type);
		List<HomeData> list = homeService.findList(h);
		
		model.addAttribute("homeList", list);
		model.addAttribute("eavinlau", "eavinlau-"+type);
		return "home/main";
	}
	
	//视频观看列表页
	@RequestMapping("/typelist")
	public String typelist(Model model,HttpServletRequest request){
		String id = request.getParameter("id");
		HomeData h = homeService.get(id);
		logger.info("watch---"+h.getType()+"---"+h.getName());
		h.setView(h.getView()+1);
		homeService.update(h);
		model.addAttribute("homeData", h);
		List<HomeData> list = homeService.findList(h);
		
		model.addAttribute("homeList", list);
		return "home/list";
	}
	
	//搜索视频
	@RequestMapping("/search")
	public String search(Model model,HttpServletRequest request){
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String searchStr = request.getParameter("searchStr");
		List<HomeData> list = homeService.findSearchList(searchStr);
		
		model.addAttribute("homeList", list);
		model.addAttribute("eavinlau", "eavinlau-s");
		return "home/main";
	}
	
	//跳转到添加视频
	@RequestMapping("goAdd")
	public String goAdd(Model model,HttpServletRequest request){
		
		return "home/add";
	}
	
	//保存视频
	@RequestMapping("/save")
	public String save(Model model,HomeData h,MultipartFile[] mp4File,HttpServletRequest request) throws Exception{
		if(mp4File.length>0){
			for(MultipartFile myfile : mp4File){  
	            if(myfile.isEmpty()){  
	                logger.info("文件未上传");  
	            }else{  
	            	logger.info("========================================");  
	                logger.info("文件长度: " + myfile.getSize());  
	                logger.info("文件类型: " + myfile.getContentType());  
	                logger.info("文件名称: " + myfile.getName());  
	                logger.info("文件原名: " + myfile.getOriginalFilename());  
	                logger.info("========================================");  
	                //如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中  
	                String realPath = request.getSession().getServletContext().getRealPath("/static/mp4/"+h.getType()); 
	                logger.warn("文件存放绝对路径：：："+realPath);
	                File f = new File(realPath);
	                if(!f.exists()){
	                	f.mkdirs();
	                }
	    			String fileName = myfile.getOriginalFilename();
//	    			long l = System.nanoTime();
//	    			String ext = fileName.substring(fileName.lastIndexOf("."));
//	    			String name = l+"--"+fileName;
	    			
	                //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的  
	    			myfile.transferTo(new File(realPath, fileName));
//	    			FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, fileName));  
	                h.setName(fileName);
	                logger.info("upload---"+realPath+File.separator+fileName);  
	            }  
	        }  
		}
		homeService.insert(h);
		List<HomeData> list = homeService.findAllList();
		
		model.addAttribute("homeList", list);
		model.addAttribute("eavinlau", "eavinlau");
		return "home/main";
	}
	
	//跳转到删除视频页面
	@RequestMapping("/goDel")
	public String goDelList(Model model,HttpServletRequest request){
		List<HomeData> list = homeService.findAllList();
		model.addAttribute("homeList", list);
		
		return "home/del";
	}
	
	//删除视频
	@RequestMapping("/del")
	public String del(Model model,HttpServletRequest request){
		String id = request.getParameter("id");
		HomeData h = homeService.get(id);
		homeService.delete(id);
		String realPath = request.getSession().getServletContext().getRealPath("/static/mp4/"+h.getType()+"/"+h.getName());
		File f = new File(realPath);
		if(f.exists()){
        	f.delete();
        }
		logger.info("delete---"+realPath);  
		List<HomeData> list = homeService.findAllList();
		model.addAttribute("homeList", list);
		
		return "home/del";
	}
	
	//检查title是否重复
	@RequestMapping("/checkTitle")
	public @ResponseBody Object checkTitle(Model model,HttpServletRequest request,HttpServletResponse response){
		String title = request.getParameter("title");
		List<HomeData> list = homeService.getListByTitle(title);
		String res="ok";
		if(list!=null&&list.size()>0){
			res="repeat";
		}
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("res", res);
		return map;
	}
	
//	@ExceptionHandler
//    public String exp(Exception ex) {
//		logger.error(getTrace(ex));
//		return "error/500";
//    }
//    
//    private String getTrace(Throwable t) {
//		StringWriter stringWriter = new StringWriter();
//		PrintWriter writer = new PrintWriter(stringWriter);
//		t.printStackTrace(writer);
//		return stringWriter.getBuffer().toString();
//	}
}
