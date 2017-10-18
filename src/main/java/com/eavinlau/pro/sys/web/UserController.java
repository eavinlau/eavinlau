package com.eavinlau.pro.sys.web;

import java.io.InputStream;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.eavinlau.fw.util.MD5;
import com.eavinlau.pro.home.entity.HomeData;
import com.eavinlau.pro.home.service.HomeService;
import com.eavinlau.pro.sys.entity.UserData;
import com.eavinlau.pro.sys.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	private static Logger logger = Logger.getLogger(UserController.class); 
	
	@Autowired
	HomeService homeService;
	
	@Autowired
	UserService userService;
	
	public static final int WINDOW_SIZE = 4;
	
	public static final int SECRET_SIZE = 10;
    
    public static final String SEED = "g8GjEvTbW5oVSV7avLBdwIHqGlUYNzKFI7izOF8GwLDVKs2m0QN7vxRs2im5MDaNCWGmcD2rvcZx";
    
    public static final String RANDOM_NUMBER_ALGORITHM = "SHA1PRNG";
    
    public static String su = "wn;";
    
    public static Map<String, Long> timeK = new HashMap<String, Long>();
    
    //登录
  	@RequestMapping("/main")
  	public String main(Model model,HttpServletRequest request){
  		String username = (String)request.getSession().getAttribute("username");
  		List<UserData> list = userService.getUserByName(username);
  		if(list!=null&&list.size()==1){
  			model.addAttribute("user", list.get(0));
  		}else{
  			model.addAttribute("user", new UserData());
  		}
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
		List<UserData> list = userService.getUserByNamePD(username, MD5.GetMD5Code(password));
		if(list!=null&&list.size()==1){
//			if(su.contains(username+";")){
//				model.addAttribute("msg", "该账户已在其他终端登录");
//				return "sys/login";
//			}else{
//				su=su+username+";";
//			}
//			timeK.put(username, System.currentTimeMillis());
			List<HomeData> l = homeService.findAllList();
			
			model.addAttribute("homeList", l);
			model.addAttribute("eavinlau", "eavinlau");
			model.addAttribute("msg", "");
			logger.info("欢迎---------"+username);
			request.getSession().setAttribute("username", username);
			return "home/main";
		}else{
			model.addAttribute("msg", "密码不正确");
			return "sys/login";
		}
	}
	
	//检查验证码是否正确
	@RequestMapping("/checkCode")
	public void checkCode(Model model,HttpServletRequest request,HttpServletResponse response){
		String username = request.getParameter("username");
		String googleCode=request.getParameter("googleCode");
		List<UserData> list = userService.getUserByName(username);
		
		String res = "";
		String msg = "";
		
		if(list!=null&&list.size()==1){
			long t = System.currentTimeMillis();
	        boolean r = check_code(list.get(0).getGoogleCode(), Long.parseLong(googleCode), t);
	        r=true;
	        if(r){
	        	res="yes";
				msg="";
	        }else{
	        	res="no";
				msg="验证码错误";
	        }
		}else{
			res="no";
			msg="用户名不存在";
		}
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("res", res);
		map.put("msg", msg);
		JSONObject obj = new JSONObject(map);
		response.setCharacterEncoding("UTF8");
		PrintWriter printWriter = null;
		try {
			printWriter = response.getWriter();
			printWriter.print(obj.toString());
		} catch(Exception e) {
			logger.error(e.getMessage());
		}finally {
			if (null != printWriter) {
				printWriter.flush();
				printWriter.close();
			}
		}
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
		String googleCode=generateSecretKey();
		UserData u = new UserData();
		u.setUsername(username);
		u.setPassword(MD5.GetMD5Code(password));
		u.setGoogleCode(googleCode);
		userService.register(u);
		
		List<HomeData> list = homeService.findAllList();
		
		model.addAttribute("homeList", list);
		model.addAttribute("eavinlau", "eavinlau");
		return "home/main";
	}
		
	//检查用户名是否重复
	@RequestMapping("/checkUsername")
	public void checkUsername(Model model,HttpServletRequest request,HttpServletResponse response){
		InputStream is = null;
		String pramStr = null;
		try {
			is = request.getInputStream();
			pramStr = IOUtils.toString(is, "utf-8");
			logger.info(pramStr);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		String username = JSONObject.parseObject(pramStr).getString("username");
		List<UserData> list = userService.getUserByName(username);
		String res="ok";
		if(list!=null&&list.size()>0){
			res="repeat";
		}
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("res", res);
		JSONObject obj = new JSONObject(map);
		response.setCharacterEncoding("UTF8");
		PrintWriter printWriter = null;
		try {
			printWriter = response.getWriter();
			printWriter.print(obj.toString());
		} catch(Exception e) {
			logger.error(e.getMessage());
		}finally {
			if (null != printWriter) {
				printWriter.flush();
				printWriter.close();
			}
		}
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
		List<HomeData> list = homeService.findAllList();
		
		model.addAttribute("homeList", list);
		model.addAttribute("eavinlau", "eavinlau");
		request.getSession().setAttribute("username", null);
		return "home/main";
	}
	
	public boolean check_code(String secret, long code, long timeMsec) {
	        Base32 codec = new Base32();
	        byte[] decodedKey = codec.decode(secret);
	        long t = (timeMsec / 1000L) / 30L;
	        for (int i = -WINDOW_SIZE; i <= WINDOW_SIZE; ++i) {
	            long hash;
	            try {
	                hash = verify_code(decodedKey, t + i);
	            }catch (Exception e) {
	                e.printStackTrace();
	                throw new RuntimeException(e.getMessage());
	            }
	            if (hash == code) {
	                return true;
	            }
	        }
	        return false;
	   }
	    
	   private static int verify_code(byte[] key, long t) throws NoSuchAlgorithmException, InvalidKeyException {
	        byte[] data = new byte[8];
	        long value = t;
	        for (int i = 8; i-- > 0; value >>>= 8) {
	            data[i] = (byte) value;
	        }
	        SecretKeySpec signKey = new SecretKeySpec(key, "HmacSHA1");
	        Mac mac = Mac.getInstance("HmacSHA1");
	        mac.init(signKey);
	        byte[] hash = mac.doFinal(data);
	        int offset = hash[20 - 1] & 0xF;
	        long truncatedHash = 0;
	        for (int i = 0; i < 4; ++i) {
	            truncatedHash <<= 8;
	            truncatedHash |= (hash[offset + i] & 0xFF);
	        }
	        truncatedHash &= 0x7FFFFFFF;
	        truncatedHash %= 1000000;
	        return (int) truncatedHash;
	    }
	   
	   //生成验证码
	   public static String generateSecretKey() {
	        SecureRandom sr = null;
            try {
				sr = SecureRandom.getInstance(RANDOM_NUMBER_ALGORITHM);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
            sr.setSeed(Base64.decodeBase64(SEED));
            byte[] buffer = sr.generateSeed(SECRET_SIZE);
            Base32 codec = new Base32();
            byte[] bEncodedKey = codec.encode(buffer);
            String encodedKey = new String(bEncodedKey);
            return encodedKey;
	    }
}
