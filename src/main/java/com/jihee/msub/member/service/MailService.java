package com.jihee.msub.member.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.jihee.msub.member.dto.MailDto;

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
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(mailDto.getAddress());
		message.setFrom(fromAddress);
		message.setSubject(mailDto.getTitle());
		message.setText(mailDto.getMessage());
		
		mailSender.send(message);
	}
}
