package org.nexters.inhousekitchen.controller;

import javax.annotation.Resource;

import org.nexters.inhousekitchen.dto.BookingDTO;
import org.nexters.inhousekitchen.service.BookingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
//@RequestMapping(value="/api")
//@Api(value="예약 컨트롤러")
@Controller
public class BookingController {

	@Resource
	BookingService bookingService;
	
	/*예약하기*/
	@ApiOperation(value="예약하기 기능")
	@RequestMapping(value="/booking", method=RequestMethod.GET)
	@ResponseBody
	public void detailPage(@RequestParam BookingDTO bookingDto) {
		bookingService.booking(bookingDto);
	}
}
