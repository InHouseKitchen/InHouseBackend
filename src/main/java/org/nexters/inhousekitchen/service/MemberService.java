package org.nexters.inhousekitchen.service;

import org.nexters.inhousekitchen.dto.MemberDTO;
import org.nexters.inhousekitchen.dto.PreferDTO;
import org.nexters.inhousekitchen.exception.ServerErrorException;
import org.nexters.inhousekitchen.exception.WrongParamException;
import org.springframework.transaction.annotation.Transactional;

public interface MemberService {
	//회원관리 관련 서비스 만드는 곳입니다
	
	//이런식으로
	//MemberDTO login(String ID, int password);
	
	//MemberDTO logout(String ID, int password);
	
	//MemberDTO register();
	
	@Transactional
	void registerMember(MemberDTO member, PreferDTO prefer) throws ServerErrorException;
	
	MemberDTO getMemberByUserName(String username) throws ServerErrorException;
	MemberDTO getMemberByEmail(String email) throws ServerErrorException;
	
}
