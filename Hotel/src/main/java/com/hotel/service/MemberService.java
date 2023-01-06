package com.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotel.mapper.MemberMapper;
import com.hotel.model.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService{
	
	@Autowired
	private final PasswordEncoder passwordEncoder;
	@Autowired
	private final MemberMapper mMapper;
	
	@Transactional
	public void MemberAdd(Member m) {
						
		m.setM_pwd(passwordEncoder.encode(m.getM_pwd()));
		mMapper.MemberAdd(m);
	}
	
	

}
