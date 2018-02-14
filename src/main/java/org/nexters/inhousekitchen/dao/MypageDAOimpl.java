package org.nexters.inhousekitchen.dao;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.nexters.inhousekitchen.dto.BookingDTO;
import org.nexters.inhousekitchen.dto.DiningDTO;
import org.nexters.inhousekitchen.dto.ReviewDTO;
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

	@Override
	public List<BookingDTO> getBookingList(String userId) {
		return template.selectList("mypage.getBookingList", userId);
	}

	@Override
	public List<ReviewDTO> getReviewList(String userId) {
		return template.selectList("mypage.getReviewList", userId);
	}

	@Override
	public void statusChange(Integer hostId) {
		template.update("mypage.statusChange", hostId);
	}

	
	
}
