package org.nexters.inhousekitchen.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.nexters.inhousekitchen.dto.DiningDTO;
import org.nexters.inhousekitchen.exception.ServerErrorException;
import org.nexters.inhousekitchen.service.SearchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.fabric.xmlrpc.base.Array;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@RestController
@RequestMapping("/search")
@Api(value="/search", description="홈 & 검색 & 호스트 상세조회")

public class SearchController {

	@Resource
	SearchService searchService;
		
	/*메인페이지 */
	@ApiOperation(httpMethod="GET", value="위치검색 전 홈에서 메뉴 조회(Internal Server Error 발생 시 빈 배열 반환)")
	@RequestMapping(value="/main", method=RequestMethod.GET, produces = { "application/json" })
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error", response=List.class),
		      				   @ApiResponse(code = 200, message = "OK", response=List.class)})
	@ResponseBody
	public ResponseEntity<List> mainPage() {
		
		List<DiningDTO> menuList;
		
		try {
			menuList = searchService.getHomeMenues(1);
			for(DiningDTO element: menuList) {
				element.getDiningImages().get(0).setId(null);
				element.getDiningImages().get(0).setDiningId(null);
			}
			return new ResponseEntity<List>(menuList, HttpStatus.OK);
		}catch(ServerErrorException e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<List>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	/*메뉴 검색*/
	

}
