package org.nexters.inhousekitchen.service;

import javax.annotation.Resource;

import org.nexters.inhousekitchen.dao.MypageDAO;
import org.nexters.inhousekitchen.dto.DiningDTO;
import org.springframework.stereotype.Service;
@Service
public class MypageServiceimpl implements MypageService {

	@Resource
	MypageDAO mypageDAO;

	@Override
	public void regHostMenu(DiningDTO dining) {
		mypageDAO.regHostMenu(dining);
	}
	
	
}
