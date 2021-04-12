package com.livi.coding.challenge.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.livi.coding.challenge.demo.interceptor.LoggerInterceptor;


/**
 * The Class AppInterceptorConfig.
 */
@Configuration
@Component("appInterceptorConfig")
public class AppInterceptorConfig implements WebMvcConfigurer {
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry)
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		InterceptorRegistration addInterceptor = registry.addInterceptor(new LoggerInterceptor());

		addInterceptor.excludePathPatterns("/index");
		addInterceptor.excludePathPatterns("/error");
		addInterceptor.excludePathPatterns("/null/**");
		addInterceptor.excludePathPatterns("/swagger-resources/**");
		addInterceptor.excludePathPatterns("/swagger-resources");
		addInterceptor.excludePathPatterns("/webjars/**");
		addInterceptor.excludePathPatterns("/static/**");
//		addInterceptor.excludePathPatterns("/creditservice/**"); // exclude creditService restful call

		addInterceptor.addPathPatterns("/**");

	}
}