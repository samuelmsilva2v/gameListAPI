package com.example.demo.infrastructure.configurations;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.infrastructure.filters.JwtBearerFilter;

@Configuration
public class JwtBearerConfig {

	@Bean
	FilterRegistrationBean<JwtBearerFilter> jwtFilter() {

		FilterRegistrationBean<JwtBearerFilter> filter = new FilterRegistrationBean<JwtBearerFilter>();
		
		filter.setFilter(new JwtBearerFilter());
		filter.addUrlPatterns("/api/games/*");

		return filter;
	}
}
