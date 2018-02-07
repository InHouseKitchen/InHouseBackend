package org.nexters.inhousekitchen.controller;

import java.util.List;

import javax.annotation.Resource;


import org.nexters.inhousekitchen.dto.DiningDTO;
import org.nexters.inhousekitchen.exception.ServerErrorException;
import org.nexters.inhousekitchen.service.SearchService;
import org.nexters.inhousekitchen.service.SearchServiceimpl;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/search")	
public class SearchController {

	@Resource
	SearchService searchService;
		
	/*메인페이지 */
	@ApiOperation(value="위치검색 전 홈에서 메뉴 조회")
	@RequestMapping(value="/main", method=RequestMethod.GET)
    @ApiImplicitParam(name="accessToken", value="구글 로그인 토큰", required=false, dataType="string", paramType="header")
	@ResponseBody
	public List<DiningDTO> mainPage() throws ServerErrorException {
		List<DiningDTO> menuList = null;
		try {
			menuList = searchService.getHomeMenues();
			return menuList;
		}catch(ServerErrorException e) {
			System.out.println(e.getMessage());
			throw e;
		}
		
	}
}
