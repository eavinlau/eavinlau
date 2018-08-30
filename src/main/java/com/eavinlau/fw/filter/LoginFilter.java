package com.eavinlau.fw.filter;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eavinlau.pro.sys.web.UserController;

public class LoginFilter implements Filter {

	private Timer timer = null; 
	private long count = 1; 
	
    public LoginFilter() {
    	System.out.println("实例过滤器！！！");
    }

	public void destroy() {
		System.out.println("销毁过滤器！！！");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		 
		 HttpServletRequest req=(HttpServletRequest) request;
		 HttpServletResponse res=(HttpServletResponse) response;

		 //获得请求的URL
		 String url=req.getRequestURL().toString();

		 //获得session中的对象
		 HttpSession session= req.getSession();
		 String username=(String) session.getAttribute("username");
		 System.out.println("会话ID："+session.getId()+"-用户名："+session.getAttribute("username"));
		 
//		 if(username!=null&&!UserController.su.contains(username)){
//			 username=null;
//		 }
		 
		 //使用endsWith()判断url结尾的字符串
		 if(username!=null||url.contains("static")||url.endsWith("login")||url.endsWith("checkCode")||url.endsWith("goLogin")){
			 //满足条件就继续执行
			 chain.doFilter(request, response);
		 }else{
			 res.sendRedirect(req.getContextPath() + "/user/goLogin");
		 }
	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("初始过滤器！！！");
//		timer = new Timer();
//		System.out.println("创建计时对象！！！");
//		timer.schedule(new TimerTask() {
//			@Override
//			public void run() {
//				String[] usernames = UserController.su.split(";");
//				System.out.println(UserController.su);
//				for(int i=1;i<usernames.length;i++){
//					Long t=UserController.timeK.get(usernames[i]);
//					if((System.currentTimeMillis()-t)>1000*60*30){
//						UserController.su=UserController.su.replace(usernames[i]+";", "");
//						System.out.println("清除用户:"+usernames[i]);
//						System.out.println("第"+count+"次清除用户集！！！");
//						count++;
//					}
//				}
//			}
//		}, 1000*5, 1000*10);
	}

}
