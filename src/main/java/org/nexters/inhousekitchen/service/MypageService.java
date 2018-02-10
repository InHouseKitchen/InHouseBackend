package org.nexters.inhousekitchen.service;

import java.util.List;

import org.nexters.inhousekitchen.dto.BookingDTO;
import org.nexters.inhousekitchen.dto.DiningDTO;
import org.nexters.inhousekitchen.dto.ReviewDTO;

public interface MypageService {

	void regHostMenu(DiningDTO dining);

	List<BookingDTO> getBookingList(String userId);

	List<ReviewDTO> getReviewList(String userId);

	

	

	

	//MypageService 인터페이스
	
}
