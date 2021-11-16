package com.jihee.msub.member.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.jihee.msub.member.dto.MailDto;
import com.jihee.msub.util.MailHandler;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class MailService {

	@NonNull
	private JavaMailSender mailSender;
	
	@Value("${spring.mail.username}")
	private String fromAddress;
	
	
	public void mailSend( MailDto mailDto) {
//		SimpleMailMessage message = new SimpleMailMessage();
//		message.setTo(mailDto.getAddress());
//		message.setFrom(fromAddress);
//		message.setSubject(mailDto.getTitle());
//		message.setText(mailDto.getMessage());
//		
//		mailSender.send(message);
		
		
		try {
			MailHandler mailHandler = new MailHandler(mailSender);
			mailHandler.setTo(mailDto.getAddress());
			mailHandler.setFrom(fromAddress);
			mailHandler.setSubject(mailDto.getTitle());
			
			String htmlContent = "<p>" + mailDto.getMessage() + "</p><img src=''>";
			mailHandler.setText(htmlContent, true);
			
			mailHandler.setAttach("newTest.txt", "static/originTest.txt");
			mailHandler.setInline("sample-img", "static/test.PNG");
			
			mailHandler.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
