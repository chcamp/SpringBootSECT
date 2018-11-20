package com.chris.component;

//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


@Component("myInterceptor")
public class MyInterceptor extends HandlerInterceptorAdapter {
	
	
	//calcular el tiempo y mostrarlo en el log
	private static final Log LOG = LogFactory.getLog(MyInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		request.setAttribute("startTime", System.currentTimeMillis());
		
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		long startTime =  (long) request.getAttribute("startTime");
		
		System.out.println("ACA cae en afterCompletion");
		
	}
	
	
}
