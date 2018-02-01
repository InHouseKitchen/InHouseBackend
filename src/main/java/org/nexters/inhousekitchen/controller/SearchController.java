package org.nexters.inhousekitchen.controller;

import javax.annotation.Resource;

import org.nexters.inhousekitchen.service.SearchService;
import org.springframework.stereotype.Controller;

@Controller
public class SearchController {

	@Resource
	SearchService searchService;
	
}
