package org.nexters.inhousekitchen.controller;

import javax.annotation.Resource;

import org.nexters.inhousekitchen.service.MemberService;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

	@Resource
	MemberService member;
	
	
	
}
