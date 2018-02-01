package org.nexters.inhousekitchen.service;

import javax.annotation.Resource;

import org.nexters.inhousekitchen.dao.BookingDAO;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceimpl implements BookingService {

	@Resource
	BookingDAO bookingDAO;
	
}
