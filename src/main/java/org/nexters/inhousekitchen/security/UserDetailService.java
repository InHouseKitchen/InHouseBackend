package org.nexters.inhousekitchen.security;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.nexters.inhousekitchen.dto.MemberDTO;
import org.nexters.inhousekitchen.exception.ServerErrorException;
import org.nexters.inhousekitchen.service.MemberService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailService implements UserDetailsService {
	
	@Resource
	MemberService memberService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		MemberDTO member;
		try {
			member = memberService.getMemberByUserName(username);
			if(member==null) 
				throw new UsernameNotFoundException(username);
			else {
				List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(); 
				UserDetails user = new UserDetails(member.getUserName(), member.getPwd(), authorities);
				
				return user;
			}
		} catch (ServerErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
