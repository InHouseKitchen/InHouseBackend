package org.nexters.inhousekitchen.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.nexters.inhousekitchen.dto.DiningDTO;
import org.nexters.inhousekitchen.exception.ServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
@Repository

public class SearchDAOimpl implements SearchDAO {

	@Resource
	SqlSessionTemplate template;
	
	
	/*메인페이지에서 임의의 메뉴들을 조회*/
	@Override
	public List<DiningDTO> selectDining() throws ServerErrorException {
		List<DiningDTO> result = null;
		try {
			result = template.selectList("dining.selectRandom"); 
		}catch(Exception e) {
			throw new ServerErrorException(e.getMessage());
		}
		return result;
	}
	
}
