package com.jihee.msub.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private String version;
	private String title;
	
	@Bean
	public Docket apiV1() {
		version = "V1";
		title = "My closet API "+ version;
		
		List<ResponseMessage> responseMessages = new ArrayList<>();
		responseMessages.add(new ResponseMessageBuilder()
				.code(200)
				.message("OK!!")
				.build());
		responseMessages.add(new ResponseMessageBuilder()
				.code(404)
				.message("Not Found!")
				.build());
		responseMessages.add(new ResponseMessageBuilder()
				.code(500)
				.message("Internal Server Error!")
				.build());
		
		return new Docket(DocumentationType.SWAGGER_2)
				.useDefaultResponseMessages(false)		//false로 설정해 swagger에서 제공해주는 응답코드에 대한 기본 메시지 제거
				.groupName(version)					//Docket Bean이 여러개인 경우 groupname이 충돌하지 않도록 명시해줌
				.select()							//ApiSelectorBuilder 생성
				.apis(RequestHandlerSelectors.basePackage("com.jihee.msub.board.controller"))	//컨트롤러가 작성되어 있는 패키지 설정.
				.paths(PathSelectors.ant("/board/**"))		//apis()에서 선택되어진 API중 특정 path조건에 맞는 API들을 다시 필터링
				.build()
				.apiInfo(apiInfo(title,version)) 		//제목 ,설명 등 문서에 대한 정보들을 보여주기 위해 호출
				.globalResponseMessage(RequestMethod.GET, responseMessages);
		
	}
	
	@Bean
	public Docket apiV2() {
		version = "V2";
		title = "My closet API "+ version;
		
		return new Docket(DocumentationType.SWAGGER_2)
				.useDefaultResponseMessages(false)		//false로 설정해 swagger에서 제공해주는 응답코드에 대한 기본 메시지 제거
				.groupName(version)					//Docket Bean이 여러개인 경우 groupname이 충돌하지 않도록 명시해줌
				.select()							//ApiSelectorBuilder 생성
				.apis(RequestHandlerSelectors.basePackage("com.jihee.msub.member.controller"))	//컨트롤러가 작성되어 있는 패키지 설정.
				.paths(PathSelectors.ant("/**"))		//apis()에서 선택되어진 API중 특정 path조건에 맞는 API들을 다시 필터링
				.build()
				.apiInfo(apiInfo(title,version)); 		//제목 ,설명 등 문서에 대한 정보들을 보여주기 위해 호출
	}
	
	
	
	private ApiInfo apiInfo(String title, String version) {
		return new ApiInfo(
				title, 
				"Java Spring boot Jpa 프로젝트",
				version,
				"www.example.com",
				new Contact("Contact Me", "www.example.com", "leejhdev922@gmail.com"), 
				"Licenses",
				"www.example.com",
				new ArrayList<>());
				
	}
	
	
	
	
}
