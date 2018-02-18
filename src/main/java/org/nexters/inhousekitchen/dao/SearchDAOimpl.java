package org.nexters.inhousekitchen.dao;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.nexters.inhousekitchen.dto.DiningDTO;
import org.nexters.inhousekitchen.dto.PreferDTO;
import org.nexters.inhousekitchen.dto.ReviewDTO;
import org.nexters.inhousekitchen.exception.ServerErrorException;
import org.springframework.stereotype.Repository;
@Repository

public class SearchDAOimpl implements SearchDAO {

	@Resource
	SqlSessionTemplate template;
	
	
	/*메인페이지에서 로그아웃 상태일 때 임의의 메뉴들을 조회*/
	@Override
	public List<DiningDTO> selectByRandom() throws ServerErrorException {
		List<DiningDTO> result = null;
		try {
			result = template.selectList("dining.selectByRandom"); 
		}catch(Exception e) {
			e.printStackTrace();
			throw new ServerErrorException(e.getMessage());
		}
		return result;
	}
	
	
	/* 메인화면에서 로그인 상태일 때 로그인한 사용자의 favor에 기반한 메뉴들을 조회 */
	@Override
	public List<DiningDTO> selectByPrefer(PreferDTO prefer) throws ServerErrorException{
		List<DiningDTO> result = null;
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("prefer", prefer);
			result = template.selectList("dining.selectByPrefer", map);
		}catch(Exception e) {
			throw new ServerErrorException(e.getMessage());
		}
		return result;
	}
	
	/* 상세보기 페이지 정보 가져오기*/
	@Override
	public List<DiningDTO> getDetailInfo(int hostId) {
		return template.selectList("dining.getDetailInfo", hostId);
	}

	 /* Review 정보 가져오기*/
	@Override
	public List<ReviewDTO> getReview(int diningId) {
		return template.selectList("dining.getReview");
	}
}
