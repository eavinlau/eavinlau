package com.eavinlau.pro.web;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.eavinlau.fw.util.GoogleUtil;
import com.eavinlau.fw.util.MD5Util;
import com.eavinlau.pro.entity.HomeData;
import com.eavinlau.pro.entity.UserData;
import com.eavinlau.pro.service.HomeService;
import com.eavinlau.pro.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	private static Logger logger = Logger.getLogger(UserController.class); 
	
	@Autowired
	HomeService homeService;
	
	@Autowired
	UserService userService;
	
    public static String su = "wn;";
    
    public static Map<String, Long> timeK = new HashMap<String, Long>();
    
    //用户个人页面
  	@RequestMapping("/main")
  	public String main(Model model,HttpServletRequest request){
  		return "sys/main";
  	}
  	
    //跳转到登录页面
    @RequestMapping("/goLogin")
	public String goLogin(Model model,HttpServletRequest request){
		model.addAttribute("msg", "");
		return "sys/login";
	}
    
    //登录
	@RequestMapping("/login")
	public String login(Model model,HttpServletRequest request){
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		List<UserData> list = userService.getUserByNamePD(username, MD5Util.GetMD5Code(password));
		if(list!=null&&list.size()==1){
			List<HomeData> l = homeService.findAllList();
			
			model.addAttribute("homeList", l);
			model.addAttribute("eavinlau", "eavinlau");
			model.addAttribute("msg", "");
			logger.info("欢迎---------"+username);
			request.getSession().setAttribute("user", list.get(0));
			return "home/main";
		}else{
			model.addAttribute("msg", "用户名或密码错误");
			return "sys/login";
		}
	}
	
	//检查验证码是否正确
	@ResponseBody
	@RequestMapping("/checkCode")
	public Object checkCode(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String username = request.getParameter("username");
		String googleCode=request.getParameter("googleCode");
		List<UserData> list = userService.getUserByName(username);
		
		String res = "";
		String msg = "";
		
		if(list!=null&&list.size()==1){
			long t = System.currentTimeMillis();
	        boolean r = GoogleUtil.check_code(list.get(0).getGoogleCode(), Long.parseLong(googleCode), t);
	        if(r){
	        	res="yes";
				msg="";
	        }else{
	        	res="no";
				msg="谷歌验证码错误";
	        }
		}else{
			res="no";
			msg="用户不存在";
		}
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("res", res);
		map.put("msg", msg);
		return map;
	}
	
	//跳转到注册
	@RequestMapping("/goRegister")
	public String goRegister(Model model,HttpServletRequest request,HttpServletResponse response){
		return "sys/register";
	}
	
	//注册
	@RequestMapping("/register")
	public String register(Model model,HttpServletRequest request){
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String type=request.getParameter("type");
		String googleCode=GoogleUtil.generateSecretKey();
		UserData u = new UserData();
		u.setUsername(username);
		u.setPassword(MD5Util.GetMD5Code(password));
		u.setType(type);
		u.setGoogleCode(googleCode);
		userService.register(u);
		
		List<HomeData> list = homeService.findAllList();
		
		model.addAttribute("homeList", list);
		model.addAttribute("eavinlau", "eavinlau");
		return "home/main";
	}
		
	//检查用户名是否重复
	@ResponseBody
	@RequestMapping("/checkUsername")
	public Object checkUsername(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		InputStream is = request.getInputStream();
		String pramStr = IOUtils.toString(is, "utf-8");
		logger.info(pramStr);
		String username = JSONObject.parseObject(pramStr).getString("username");
		List<UserData> list = userService.getUserByName(username);
		String res="ok";
		if(list!=null&&list.size()>0){
			res="repeat";
		}
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("res", res);
		return map;
	}
		
	//跳转到删除用户页面
	@RequestMapping("/goDel")
	public String goDelList(Model model,HttpServletRequest request){
		List<UserData> list = userService.findAllList();
		model.addAttribute("userList", list);
		return "sys/del";
	}
	
	//删除用户
	@RequestMapping("/del")
	public String del(Model model,HttpServletRequest request){
		String id = request.getParameter("id");
		userService.delete(id);
		List<UserData> list = userService.findAllList();
		model.addAttribute("userList", list);
		return "sys/del";
	}
		
	//退出登录
	@RequestMapping("/exit")
	public String exit(Model model,HttpServletRequest request,HttpServletResponse response){
		logger.info("exit！！！");
		request.getSession().removeAttribute("user");
		return "sys/login";
	}
	
}
