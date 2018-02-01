package org.nexters.inhousekitchen.service;

import javax.annotation.Resource;

import org.nexters.inhousekitchen.dao.MemberDAO;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceimpl implements MemberService {
	
	@Resource
	MemberDAO memberDAO;
	
	//메서드 만들기
	
	
}
