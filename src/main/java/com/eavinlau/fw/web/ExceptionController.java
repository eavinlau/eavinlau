package com.eavinlau.fw.web;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice  
public class ExceptionController {  
	private static Logger logger = Logger.getLogger(ExceptionController.class); 
	
    //基于@ExceptionHandler异常处理 
    @ExceptionHandler  
    public String handleException(Exception e) {  
        // 根据不同错误转向不同页面  
//        if(e instanceof BusinessException) {  
//            return "error/business_error";  
//        }else if(e instanceof ParameterException) {  
//            return "error/parameter_error";  
//        } else {  
//            return "error/500";  
//        }  
    	logger.error("出错啦", e);
        return "error/500"; 
    }  
}
