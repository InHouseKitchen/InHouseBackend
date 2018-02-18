package org.nexters.inhousekitchen.service;

import javax.annotation.Resource;

import org.nexters.inhousekitchen.dao.BookingDAO;
import org.nexters.inhousekitchen.dto.BookingDTO;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceimpl implements BookingService {

	@Resource
	BookingDAO bookingDAO;
	
	/*예약하기*/
	@Override
	public void booking(BookingDTO bookingDto) {
		bookingDAO.booking(bookingDto);
	}
}
