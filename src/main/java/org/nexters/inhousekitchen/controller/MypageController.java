package org.nexters.inhousekitchen.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.nexters.inhousekitchen.dto.BookingDTO;
import org.nexters.inhousekitchen.dto.DiningDTO;
import org.nexters.inhousekitchen.dto.DiningImageDTO;
import org.nexters.inhousekitchen.dto.ReviewDTO;
import org.nexters.inhousekitchen.service.MypageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/mypage")
public class MypageController {

	@Resource
	MypageService mypageService;
	
	//regHostMenu
	@ApiOperation(value="호스트 메뉴등록")
	@RequestMapping(value="/regMenu",method=RequestMethod.POST, params="type=DiningDTO")
	@ResponseBody
	public String regHostMenu(
			@RequestParam(value = "id", required = true) int id,
            @RequestParam(value = "hostId", required = true) int hostId,
            @RequestParam(value = "startDate", required = true) Date startDate,
            @RequestParam(value = "endDate", required = true) Date endDate,
            @RequestParam(value = "longitude", required = true) Double longitude,
            @RequestParam(value = "latitude", required = true) Double latitude,
            @RequestParam(value = "price", required = true) int price,
            @RequestParam(value = "guests", required = true) int guests,
            @RequestParam(value = "dIntro", required = true) String dIntro,
            @RequestParam(value = "mIntro", required = true) String mIntro,
            @RequestParam(value = "diningImages", required = true) DiningImageDTO diningImages
			){
		
		DiningDTO dining=new DiningDTO(id, hostId, startDate, endDate, longitude, latitude, price, guests, dIntro, mIntro, diningImages);
		mypageService.regHostMenu(dining);
		/*User의 status 변경 : guest -> host*/
		mypageService.statusChange(dining.getHostId());
		
		return "성공 or 실패";
	}
	
	//getBookingList
	@ApiOperation(value="예약 내역 불러오기")
	@RequestMapping(value="/bookingList",method=RequestMethod.GET)
	@ResponseBody
	public List<BookingDTO> getBookingList(String userId){
		List<BookingDTO> bto=mypageService.getBookingList(userId);
		return bto;
	}
	
	//getReviewList
	@ApiOperation(value="리뷰 내역 불러오기")
	@RequestMapping(value="/reviewList",method=RequestMethod.GET)
	@ResponseBody
	public List<ReviewDTO> getReviewList(String userId){
		List<ReviewDTO> rto=mypageService.getReviewList(userId);
		return rto;
	}
	
	//myMenu
	
}
























