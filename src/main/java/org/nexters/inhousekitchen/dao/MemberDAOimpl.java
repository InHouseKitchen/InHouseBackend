package org.nexters.inhousekitchen.dao;

import java.util.HashMap;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.nexters.inhousekitchen.dto.MemberDTO;
import org.nexters.inhousekitchen.exception.ServerErrorException;
import org.springframework.stereotype.Repository;
@Repository
public class MemberDAOimpl implements MemberDAO {

	@Resource
	SqlSessionTemplate template;
	
	@Override
	public MemberDTO selectByUsername(String username) throws ServerErrorException{
		HashMap <String, String> map = new HashMap<String, String>();
		map.put("username", username);
		MemberDTO result = null;
		try {
			result = template.selectOne("member.selectByUsername", map);	
		}catch(Exception e) {
			e.printStackTrace();
			throw new ServerErrorException(e.getMessage());
		}
		return result;
	}
	
	@Override
	public MemberDTO selectByEmail(String email) throws ServerErrorException{
		HashMap <String, String> map = new HashMap<String, String>();
		map.put("email", email);
		MemberDTO result = null;
		try {
			result = template.selectOne("member.selectByEmail", map);	
		}catch(Exception e) {
			e.printStackTrace();
			throw new ServerErrorException(e.getMessage());
		}
		return result;
	}
	
	@Override
	public int insertUser(MemberDTO member) throws ServerErrorException{
		int insertId = 0;
		try {
			template.insert("member.insertNew", member);
			insertId = member.getId();
		}catch(Exception e) {
			e.printStackTrace();
			throw new ServerErrorException(e.getMessage());
		}
		return insertId;
	}
}
