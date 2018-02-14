package org.nexters.inhousekitchen.service;

import javax.annotation.Resource;

import org.nexters.inhousekitchen.dao.MemberDAO;
import org.nexters.inhousekitchen.dao.PreferDAO;
import org.nexters.inhousekitchen.dto.MemberDTO;
import org.nexters.inhousekitchen.dto.PreferDTO;
import org.nexters.inhousekitchen.exception.ServerErrorException;
import org.nexters.inhousekitchen.exception.WrongParamException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberServiceimpl implements MemberService {
	
	@Resource
	MemberDAO memberDAO;
	@Resource
	PreferDAO preferDAO;
	
	
	
	/*사용자 아이디로 Member 조회*/
	public MemberDTO getMemberByUserName(String username) throws ServerErrorException{
		MemberDTO usernameResult = null;
		try {
			usernameResult = memberDAO.selectByUsername(username);
			return usernameResult;
		}catch(ServerErrorException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/*사용자 이메일 주소로 Member 조회*/
	public MemberDTO getMemberByEmail(String email) throws ServerErrorException{
		MemberDTO emailResult = null;
		try {
			emailResult = memberDAO.selectByEmail(email);
			return emailResult;
		}catch(ServerErrorException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	
	/* 회원가입 */
	@Override
	public void registerMember(MemberDTO newMember, PreferDTO prefer) throws ServerErrorException{
		try{
			/*nsert 실행*/
			int userId = memberDAO.insertUser(newMember);
			/*새로 삽입된 사용자의 id를 prefer의 memberId(DB에서는 User를 참조하는 외래키)로 set*/
			prefer.setMemberId(userId);
			preferDAO.insertNew(prefer);
		}catch(ServerErrorException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	
}
