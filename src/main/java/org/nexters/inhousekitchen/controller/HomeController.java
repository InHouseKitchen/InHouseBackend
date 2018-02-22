package org.nexters.inhousekitchen.controller;



import javax.annotation.Resource;

import org.nexters.inhousekitchen.service.SearchService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping("/home")
public class HomeController {
	@Resource
	private SearchService searchService;
	
	public String home(){
		System.out.println("보빈짱");
		return "home";
	}
	

}