package com.jihee.msub.login.dto;

import java.time.LocalDateTime;

import com.jihee.msub.login.domain.entity.MemberEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDto {

	
	private Long id;
	
	@NotBlank(message="닉네임은 필수 입력 값입니다.")
	private String nickname;
	
	@NotBlank(message = "이메일은 필수 입력 값입니다.")
	@Email(message = "이메일 형식에 맞지 않습니다.")
	private String email;
	
	@NotBlank(message =  "비밀번호는 필수 입력 값입니다.")
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
			message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자~20자의 비밀번호여야 합니다.")
	private String password;
	//(?=.*[0-9]) 숫자 적어도 하나, (?=.*[a-zA-Z])영어 적어도 하나 , (?=.*\\W) 특문 적어도 하나, (?=\\S+$) 공백제거
	
	private LocalDateTime createdDate;
	
	private LocalDateTime modifiedDate;
	
	public MemberEntity toEntity() {
		return MemberEntity.builder()
				.id(id)
				.email(email)
				.password(password)
				.nickname(nickname)
				.build();
	}
	
	@Builder
	public MemberDto(Long id, String email, String password, String nickname) {
		this.id = id;
		this.nickname = nickname;
		this.email = email;
		this.password = password;
	}
	
	
}
