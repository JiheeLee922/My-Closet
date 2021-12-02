package com.jihee.msub.member.dto;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.jihee.msub.member.domain.entity.MemberEntity;

import lombok.Getter;

@Getter
public class MemberAdapter extends User implements Serializable{

	private static final long serialVersionUID = 1L;
	private MemberEntity member;
	
	public MemberAdapter(MemberEntity member, Collection<? extends GrantedAuthority>   role) {
		super(member.getEmail(), member.getPassword(),role);
		this.member = member;
	}
}
