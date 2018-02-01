package org.nexters.inhousekitchen.controller;

import javax.annotation.Resource;

import org.nexters.inhousekitchen.service.BookingService;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {

	@Resource
	BookingService bookingService;
	
}
