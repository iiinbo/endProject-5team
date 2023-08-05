package com.baemin.config;

import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.baemin.interceptor.AddressInterceptor;
import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;

@Configuration
@MapperScan("com.baemin.dao") // MyBatis 매퍼 인터페이스가 있는 패키지 설정
public class WebConfig implements WebMvcConfigurer {
	
	//@Value("${resource.path}")
	//private String resourcePath;
	
	////@Value("${upload.path}")
	private String uploadPath;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//registry.addResourceHandler(uploadPath)
			//	.addResourceLocations(resourcePath);
	}
	
	//Lucy Xss filter 적용	
    @Bean
   public FilterRegistrationBean<XssEscapeServletFilter> getFilterRegistrationBean(){
        FilterRegistrationBean<XssEscapeServletFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new XssEscapeServletFilter());
        registrationBean.setOrder(1);
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
    
    @Bean
    public AddressInterceptor addressInterceptor() {
    	return new AddressInterceptor();
    }

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(addressInterceptor())
		.addPathPatterns("/*");
	}
	

}
