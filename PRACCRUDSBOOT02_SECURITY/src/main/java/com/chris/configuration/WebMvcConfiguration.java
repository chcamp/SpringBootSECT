package com.chris.configuration;

import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.chris.component.MyInterceptor;


public class WebMvcConfiguration extends WebMvcConfigurerAdapter {
	
	
	/*
	  @Override
	  public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
	    configurer.favorPathExtension(false).
	            favorParameter(true).
	            parameterName("mediaType").
	            ignoreAcceptHeader(true).
	            useJaf(false).
	            defaultContentType(MediaType.APPLICATION_JSON).
	            mediaType("xml", MediaType.APPLICATION_XML).
	            mediaType("json", MediaType.APPLICATION_JSON);
	  }
	
	*/  
	  
		@Autowired
		@Qualifier("myInterceptor")
		private MyInterceptor myInterceptor;

		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			
			registry.addInterceptor(myInterceptor);
		}
		
}
