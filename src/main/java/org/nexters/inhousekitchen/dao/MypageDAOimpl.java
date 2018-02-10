package org.nexters.inhousekitchen.dao;

import java.util.HashMap;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.nexters.inhousekitchen.dto.DiningDTO;
import org.springframework.stereotype.Repository;
@Repository
public class MypageDAOimpl implements MypageDAO {

	@Resource
	SqlSessionTemplate template;

	@Override
	public void regHostMenu(DiningDTO dining) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("dining", dining);
		template.insert("mypage.regHostMenu", map);
	}
	
}
