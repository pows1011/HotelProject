package com.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hotel.model.Member;
import com.hotel.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private final MemberService mService;
	
	
	@GetMapping("/login")
	public String loginform() {

		return "login";
	}

	@GetMapping("/")
	public String addmemberForm() {
		
		return "addmember";
	}
	
	@PostMapping("/")
	public String addmember(Member m,@RequestParam("addr1")String addr1,@RequestParam("addr2")String addr2,@RequestParam(required = false)String addr3) {
		String address="(우편번호:"+addr1+")"+"/"+addr2+"/"+addr3;
		m.setM_address(address);
		mService.MemberAdd(m);
		
		return "redirect:/login";
		
	}

}
