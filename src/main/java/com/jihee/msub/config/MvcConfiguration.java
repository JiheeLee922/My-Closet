package com.jihee.msub.config;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class MvcConfiguration implements WebMvcConfigurer {

	/*
	 * @Override public void addResourceHandlers(final ResourceHandlerRegistry
	 * registry) { log.debug("===========mvc Configuration =======" );
	 * registry.addResourceHandler("/**")
	 * .addResourceLocations("classpath:/templates/","classpath:/static/")
	 * .setCacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES)); }
	 * 
	 */
}
