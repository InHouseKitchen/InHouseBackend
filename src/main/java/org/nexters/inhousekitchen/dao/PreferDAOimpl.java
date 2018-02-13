package org.nexters.inhousekitchen.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.nexters.inhousekitchen.dto.PreferDTO;
import org.nexters.inhousekitchen.exception.ServerErrorException;
import org.springframework.stereotype.Repository;

@Repository
public class PreferDAOimpl implements PreferDAO{
	@Resource
	SqlSessionTemplate template;
	
	@Override
	public PreferDTO selectByMemberId(int memberId) throws ServerErrorException{
		try {
			PreferDTO result = template.selectOne("prefer.selectByUserId", memberId);
			return result;
		}catch(Exception e) {
			e.printStackTrace();
			throw new ServerErrorException(e.getMessage());
		}

	}
	
	@Override
	public void insertNew(PreferDTO prefer) throws ServerErrorException{
		try {
			template.insert("prefer.insertNew", prefer);
		}catch(Exception e) {
			e.printStackTrace();
			throw new ServerErrorException(e.getMessage());
		}
	}
}
