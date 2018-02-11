package org.nexters.inhousekitchen.controller;

import java.util.List;

import javax.annotation.Resource;

import org.nexters.inhousekitchen.dto.DiningDTO;
import org.nexters.inhousekitchen.exception.ServerErrorException;
import org.nexters.inhousekitchen.service.SearchService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@RestController
@RequestMapping("/search")
@Api(value="/search", description="홈 화면 & 검색페이지")

public class SearchController {

	@Resource
	SearchService searchService;
		
	/*메인페이지 */
	@ApiOperation(httpMethod="GET", value="위치검색 전 홈에서 메뉴 조회(Internal Server Error 나면 NULL 반환)")
	@RequestMapping(value="/main", method=RequestMethod.GET)
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error"),
		      				   @ApiResponse(code = 200, message = "OK") })
	@ResponseBody
	public List<DiningDTO> mainPage() throws ServerErrorException {
		
		List<DiningDTO> menuList = null;
		
		try {
			menuList = searchService.getHomeMenues(1);
		}catch(ServerErrorException e) {
			System.out.println(e.getMessage());
			throw e;
		}
		return menuList;
	}
	
	/*메뉴 검색*/
	

}
