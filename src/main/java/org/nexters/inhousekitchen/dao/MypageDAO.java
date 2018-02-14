package org.nexters.inhousekitchen.dao;

import java.util.List;

import org.nexters.inhousekitchen.dto.BookingDTO;
import org.nexters.inhousekitchen.dto.DiningDTO;
import org.nexters.inhousekitchen.dto.ReviewDTO;

public interface MypageDAO {

	void regHostMenu(DiningDTO dining);

	List<BookingDTO> getBookingList(String userId);

	List<ReviewDTO> getReviewList(String userId);

	void statusChange(Integer hostId);

	

}
