package org.nexters.inhousekitchen.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.nexters.inhousekitchen.dto.HostDTO;
import org.nexters.inhousekitchen.service.SearchService;
import org.nexters.inhousekitchen.service.SearchServiceimpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping("/home")
@Api(value="swag-controller")

public class HomeController {
	
	private SearchService searchService;
	
	
	/*메인페이지(랜덤한 호스트 메뉴 조회)*/
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public String home(){
		searchService = new SearchServiceimpl();
		System.out.println("보빈짱");
		return "home";
	}
	

}