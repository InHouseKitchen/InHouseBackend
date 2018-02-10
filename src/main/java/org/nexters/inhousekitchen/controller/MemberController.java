package org.nexters.inhousekitchen.controller;

import javax.annotation.Resource;

import org.nexters.inhousekitchen.service.MemberService;
import org.nexters.inhousekitchen.service.MemberServiceimpl;
import org.nexters.inhousekitchen.service.SearchServiceimpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {

	@Resource
	MemberService memberService;
	
	
}
