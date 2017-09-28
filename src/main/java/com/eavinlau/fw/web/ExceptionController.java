package com.eavinlau.fw.web;

import java.io.PrintWriter;
import java.io.StringWriter;

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
//        if(ex instanceof BusinessException) {  
//            return "business_error";  
//        }else if(ex instanceof ParameterException) {  
//            return "parameter_error";  
//        } else {  
//            return "500";  
//        }  
    	logger.error(getTrace(e));
        return "error/500"; 
    }  
    private String getTrace(Throwable t) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		t.printStackTrace(writer);
		return stringWriter.getBuffer().toString();
	}
}
