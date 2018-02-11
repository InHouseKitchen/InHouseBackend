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
	
	
	/* 회원가입 */
	@Override
	public void registerMember(MemberDTO newMember, PreferDTO prefer) throws ServerErrorException, WrongParamException{
		MemberDTO usernameResult = null;
		MemberDTO emailResult = null;
		try{
			/* 중복되는 아이디나 이메일이 있는지 체크*/
			usernameResult = memberDAO.selectByUsername(newMember.getUserName());
			emailResult = memberDAO.selectByEmail(newMember.getEmail());
			if(usernameResult!=null) throw new WrongParamException("이미 존재하는 아이디입니다.");
			if(emailResult!=null) throw new WrongParamException("이미 존재하는 이메일입니다.");
			
			/*아이디와 이메일이 중복되지 않으면 insert 실행*/
			int userId = memberDAO.insertUser(newMember);
	
			/*새로 삽입된 사용자의 id를 prefer의 memberId(DB에서는 User를 참조하는 외래키)로 set*/
			prefer.setMemberId(userId);
			preferDAO.insertNew(prefer);
			System.out.println("inserted user : "+userId);
		}catch(ServerErrorException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	
}
