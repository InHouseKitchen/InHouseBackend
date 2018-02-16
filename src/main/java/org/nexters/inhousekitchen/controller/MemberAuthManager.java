package org.nexters.inhousekitchen.controller;

import javax.annotation.Resource;

import org.nexters.inhousekitchen.dto.MemberDTO;
import org.nexters.inhousekitchen.exception.ServerErrorException;
import org.nexters.inhousekitchen.service.MemberService;
import org.nexters.inhousekitchen.service.MemberServiceimpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class MemberAuthManager implements AuthenticationManager{
	@Resource
	MemberService memberService;
	@Resource(name="passwordEncoder")
	BCryptPasswordEncoder passwordEncoder;
	
	public Authentication authenticate(Authentication auth) throws AuthenticationException{
		MemberDTO signedMember;
		try {
			System.out.println("authenticate called");
			signedMember = memberService.getMemberByUserName(auth.getName()); 
			if(signedMember==null) throw new BadCredentialsException("회원정보가 존재하지 않습니다.");
			else {
				if(!passwordEncoder.matches(auth.getCredentials().toString(), signedMember.getPwd()))
					throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
				else return new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials());
			}
		}catch(ServerErrorException e) {
			e.printStackTrace();
			throw new BadCredentialsException("ServerError");
		}
		
	}


}
