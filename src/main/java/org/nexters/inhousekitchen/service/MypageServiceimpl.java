package org.nexters.inhousekitchen.service;

import javax.annotation.Resource;

import org.nexters.inhousekitchen.dao.MypageDAO;
import org.springframework.stereotype.Service;
@Service
public class MypageServiceimpl implements MypageService {

	@Resource
	MypageDAO mypageDAO;
	
	
}
