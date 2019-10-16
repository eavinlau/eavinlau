package com.eavinlau.fw.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.eavinlau.pro.entity.UserData;

public class LoginFilter implements Filter {

	private static Logger logger = Logger.getLogger(LoginFilter.class);

	public void destroy() {
		logger.info("销毁过滤器！！！");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		// 获得请求的URL
		String url = req.getRequestURL().toString();

		// 获得session中的对象
		HttpSession session = req.getSession();
		UserData user = (UserData) session.getAttribute("user");

		// 使用endsWith()判断url结尾的字符串
		if (user != null) {
			logger.info("会话ID：" + session.getId() + "-用户名：" + user.getUsername()+"-请求路径："+url);
			chain.doFilter(request, response);
		}else {
			if(url.contains("static") || url.endsWith("login") || url.endsWith("checkCode")
					|| url.endsWith("goLogin")) {
				chain.doFilter(request, response);
			} else {
				res.sendRedirect(req.getContextPath() + "/user/goLogin");
			}
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		logger.info("初始过滤器！！！");
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
