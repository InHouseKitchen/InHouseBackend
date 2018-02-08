package org.nexters.inhousekitchen.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.nexters.inhousekitchen.dao.SearchDAO;
import org.nexters.inhousekitchen.dao.SearchDAOimpl;
import org.nexters.inhousekitchen.dto.DiningDTO;
import org.nexters.inhousekitchen.exception.ServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SearchServiceimpl implements SearchService {

	@Resource
	SearchDAO searchDAO;
	

	/*메인 페이지에 보여줄 메뉴들을 조회*/
	public List<DiningDTO> getHomeMenues() throws ServerErrorException{
		List<DiningDTO> result = null;
		try {
			result = searchDAO.selectDining();
		}catch(Exception e) {
			throw new ServerErrorException(e.getMessage());
		}
		return result;
	}
}
