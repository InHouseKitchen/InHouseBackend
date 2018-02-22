package org.nexters.inhousekitchen.service;

import java.util.ArrayList;
import java.util.List;
import java.lang.Integer;
import javax.annotation.Resource;

import org.nexters.inhousekitchen.dao.PreferDAO;
import org.nexters.inhousekitchen.dao.SearchDAO;
import org.nexters.inhousekitchen.dao.SearchDAOimpl;
import org.nexters.inhousekitchen.dto.DiningDTO;
import org.nexters.inhousekitchen.dto.PreferDTO;
import org.nexters.inhousekitchen.dto.ReviewDTO;
import org.nexters.inhousekitchen.exception.ServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
@Service
public class SearchServiceimpl implements SearchService {

	@Resource
	SearchDAO searchDAO;
	@Resource
	PreferDAO preferDAO;

	
	/*메인 페이지에 보여줄 메뉴들을 조회*/
	@Override
	public List<DiningDTO> getHomeMenues(int memberId) throws ServerErrorException{
		List<DiningDTO> result = null;
		PreferDTO prefer = null;
		try {

			if(memberId!=0) { //로그인된 상태
				prefer = preferDAO.selectByMemberId(memberId);
				result = searchDAO.selectByPrefer(prefer);
			}
			else result = searchDAO.selectByRandom(); //로그아웃 된 상태
		}catch(Exception e) {
			e.printStackTrace();
			throw new ServerErrorException(e.getMessage());
		}
		return result;
	}
	
	/* 상세보기 페이지 정보 가져오기*/
	@Override
	public List<DiningDTO> getDetailInfo(int hostId) {
		return searchDAO.getDetailInfo(hostId);
	}

	/* Review 정보 가져오기*/
	@Override
	public List<ReviewDTO> getReview(int diningId) {
		return searchDAO.getReview(diningId);
	}
}
