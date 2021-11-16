package com.jihee.msub.util;

import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailHandler {
	
	private JavaMailSender sender;
	private MimeMessage message;
	private MimeMessageHelper messageHelper;	// spring에서 제공하는 헬퍼 객체. HTML레이아웃, 이미지삽입, 첨부파일 등 MIME 메세지 작성가능
	
	//생성자
	public MailHandler(JavaMailSender jsender) throws MessagingException {
		this.sender = jsender;
		message = jsender.createMimeMessage();
		messageHelper = new MimeMessageHelper(message, true, "UTF-8");
	}
	
	//이메일 정보 set
	public void setFrom(String fromAddress) throws MessagingException {
		messageHelper.setFrom(fromAddress);
	}
	public void setTo(String email) throws MessagingException {
		messageHelper.setTo(email);
	}
	public void setSubject(String subject) throws MessagingException {
		messageHelper.setSubject(subject);
	}
	public void setText(String text, boolean useHtml) throws MessagingException {
		messageHelper.setText(text, useHtml);
	}
	
	//첨부파일
	public void setAttach(String displayFileName , String pathToAttachment) //메일에 노출될 경로, 실제 파일 위치 
			throws MessagingException, IOException {
		File file = new ClassPathResource(pathToAttachment).getFile();
		FileSystemResource fsr = new FileSystemResource(file);
		
		messageHelper.addAttachment(displayFileName, fsr); 
	}
	
	//이미지삽입
	public void setInline(String contentId, String pathToInline) throws MessagingException, IOException {
		File file = new ClassPathResource(pathToInline).getFile();
		FileSystemResource fsr = new FileSystemResource(file);
		
		messageHelper.addInline(contentId, fsr);
	}
	
	//발송
	public void send() {
		try {
			sender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
