package org.nexters.inhousekitchen.service;

import java.util.List;

import javax.annotation.Resource;

import org.nexters.inhousekitchen.dao.MypageDAO;
import org.nexters.inhousekitchen.dto.BookingDTO;
import org.nexters.inhousekitchen.dto.DiningDTO;
import org.nexters.inhousekitchen.dto.ReviewDTO;
import org.springframework.stereotype.Service;
@Service
public class MypageServiceimpl implements MypageService {

	@Resource
	MypageDAO mypageDAO;

	@Override
	public void regHostMenu(DiningDTO dining) {
		mypageDAO.regHostMenu(dining);
	}

	@Override
	public List<BookingDTO> getBookingList(String userId) {
		return mypageDAO.getBookingList(userId);
	}

	@Override
	public List<ReviewDTO> getReviewList(String userId) {
		return mypageDAO.getReviewList(userId);
	}

	
	
	
}
