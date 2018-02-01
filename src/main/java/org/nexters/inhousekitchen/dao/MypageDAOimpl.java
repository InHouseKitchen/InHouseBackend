package org.nexters.inhousekitchen.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class MypageDAOimpl implements MypageDAO {

	@Resource
	SqlSessionTemplate template;
	
}
