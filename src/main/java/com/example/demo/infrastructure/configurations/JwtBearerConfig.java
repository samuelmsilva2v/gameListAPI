package com.example.demo.infrastructure.configurations;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.infrastructure.filters.JwtAuthFilter;
import com.example.demo.infrastructure.filters.JwtBearerFilter;

@Configuration
public class JwtBearerConfig {

	@Bean
	FilterRegistrationBean<JwtBearerFilter> adminFilter() {
		FilterRegistrationBean<JwtBearerFilter> filter = new FilterRegistrationBean<>();
		filter.setFilter(new JwtBearerFilter());
		filter.addUrlPatterns("/api/games/*");
		filter.setOrder(1);
		return filter;
	}

	@Bean
	FilterRegistrationBean<JwtAuthFilter> userFilter() {
		FilterRegistrationBean<JwtAuthFilter> filter = new FilterRegistrationBean<>();
		filter.setFilter(new JwtAuthFilter());
		filter.addUrlPatterns("/api/user-library/*");
		filter.setOrder(2);
		return filter;
	}
}
