package org.nexters.inhousekitchen.controller;

import javax.annotation.Resource;

import org.nexters.inhousekitchen.dto.DiningDTO;
import org.nexters.inhousekitchen.service.MypageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/mypage")
public class MypageController {

	@Resource
	MypageService mypageService;
	
	//regHostMenu
	@ApiOperation(value="호스트 메뉴등록")
	@RequestMapping(value="/regMenu",method=RequestMethod.POST)
	@ResponseBody
	public String regHostMenu(DiningDTO dining){
		// 메뉴를 등록하기 위해서는 어떻게??
		mypageService.regHostMenu(dining);
		
		return "성공 or 실패";
	}
	
	//getBookingList
	
	//getReviewList
	
	//myMenu
	
}
