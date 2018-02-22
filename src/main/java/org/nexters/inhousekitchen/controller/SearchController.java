package org.nexters.inhousekitchen.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.nexters.inhousekitchen.config.SessionEncoder;
import org.nexters.inhousekitchen.dto.DiningDTO;
import org.nexters.inhousekitchen.dto.MemberDTO;
import org.nexters.inhousekitchen.dto.ReviewDTO;
import org.nexters.inhousekitchen.exception.ServerErrorException;
import org.nexters.inhousekitchen.service.MemberService;
import org.nexters.inhousekitchen.service.SearchService;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@RestController
@RequestMapping(value="/search")
@Api(value="/search", description="홈 & 검색 & 호스트 상세조회")

public class SearchController {

	@Resource
	SearchService searchService;
	@Resource
	MemberService memberService;
	@Resource
	RedisTemplate<String, String> template;
	@Resource(name="redisTemplate")
	HashOperations<String, String, Authentication> hashOps;	
	@Resource
	ShaPasswordEncoder sessionEncoder;
	@Resource
	String entryKey;
	
	
	
	/*메인페이지 */
	@ApiOperation(httpMethod="GET", value="위치검색 전 홈에서 메뉴 조회(Internal Server Error 발생 시 빈 배열 반환)")
	@RequestMapping(value="/main", method=RequestMethod.GET, produces = { "application/json" })
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error", response=List.class),
		      				   @ApiResponse(code = 200, message = "OK", response=List.class)})
	@ResponseBody
	public ResponseEntity<List> mainPage(@RequestHeader(value="sessionID", required=false) String sessionID) {
		
		List<DiningDTO> menuList;
		String sessionKey = sessionID == null ? "" : sessionID;

		/* 세션에서 세션id와 엔트리 키로 Auth 정보 가져옴*/
		Authentication auth = hashOps.get(sessionKey, entryKey);
		Object principal = null;
		String username;
		
		if(auth!=null) 
			principal = auth.getPrincipal();
		
		try {
			if(principal instanceof UserDetails) 
				username = ((UserDetails)principal).getUsername();
			else username = (String)principal;
			
			/*로그인 상태라면*/
			if(username != null &&!username.equals("anonymousUser")) {
				System.out.println("username : " + username);
				MemberDTO member = memberService.getMemberByUserName(username);
				menuList = searchService.getHomeMenues(member.getId());
			}
			/*로그아웃 상태거나 세션id가 적절하지 않다면*/
			else menuList = searchService.getHomeMenues(0);
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
	
	/*상세보기 페이지*/
	@ApiOperation(httpMethod="GET", value="상세보기 페이지 기능")
	@RequestMapping(value="/detail", method=RequestMethod.GET, produces = { "application/json" })
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error", response=List.class),
		      				   @ApiResponse(code = 200, message = "OK", response=List.class)})
	@ResponseBody
	public List<DiningDTO> detailPage(@RequestParam int hostId) {
		return searchService.getDetailInfo(hostId);
	}

	/*리뷰*/
	@ApiOperation(value="리뷰")
	@RequestMapping(value="/review", method=RequestMethod.GET)
	@ResponseBody
	public List<ReviewDTO> review(@RequestParam int diningId) {
		return searchService.getReview(diningId);
	}
	

}
