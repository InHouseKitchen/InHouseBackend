package org.nexters.inhousekitchen.service;

import javax.annotation.Resource;

import org.nexters.inhousekitchen.dao.SearchDAO;
import org.springframework.stereotype.Service;
@Service
public class SearchServiceimpl implements SearchService {

	@Resource
	SearchDAO searchDAO;
	
	
}
