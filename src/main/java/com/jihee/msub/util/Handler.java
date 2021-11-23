package com.jihee.msub.util;

import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * 함수가 호출되면 Lambda는 핸들러 메서드를 실행
 * */
public class Handler implements RequestHandler<Map<String, String>, String>{

	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	/**
	 * 핸들러 메소드는 이벤트 및 컨텍스트 객체를 입력으로 받아 문자열을 반환.
	 * com.jihee.msub.util.Handler::handle
	 * */
	@Override
	public String handleRequest(Map<String, String> event, Context context) {
		
		LambdaLogger logger = context.getLogger();
		String response = new String("200 OK");
		
		logger.log("ENVIRONMENT VARIABLES : " +gson.toJson(System.getenv()));
		logger.log("CONTEXT : "+ gson.toJson(context));
		
		logger.log("EVENT : "+ gson.toJson(event));
		logger.log("EVENT TYPE : "+ event.getClass().toString());
		
		return response;
	}
	

}
