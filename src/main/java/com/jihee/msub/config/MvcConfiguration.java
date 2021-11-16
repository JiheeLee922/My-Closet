package com.jihee.msub.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class MvcConfiguration implements WebMvcConfigurer {

	
	@Bean
	public MessageSource messageSource() { //무조건 빈이름은 messageSource여야한다.
		
		//메시지 파일로 프로퍼티 형식 사용을 위한 MessageSource 구현체 클래스
		ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
		ms.setBasename("message.messages");
		ms.setDefaultEncoding("UTF-8");
		return ms;
	}
	
	
//	@Bean
//	public LocaleResolver localeResolver() {
//		SessionLocaleResolver sessionLocalResolver = new SessionLocaleResolver();
//		sessionLocalResolver.setDefaultLocale(Locale.US);
//		
//		return sessionLocalResolver;
//	}
	
	
	
}
