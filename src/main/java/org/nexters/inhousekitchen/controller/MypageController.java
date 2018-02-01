package org.nexters.inhousekitchen.controller;

import javax.annotation.Resource;

import org.nexters.inhousekitchen.service.MypageService;
import org.springframework.stereotype.Controller;

@Controller
public class MypageController {

	@Resource
	MypageService mypageService;
	
}
