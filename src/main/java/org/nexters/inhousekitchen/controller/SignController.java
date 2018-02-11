package org.nexters.inhousekitchen.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.nexters.inhousekitchen.dto.MemberDTO;
import org.nexters.inhousekitchen.dto.PreferDTO;
import org.nexters.inhousekitchen.exception.ServerErrorException;
import org.nexters.inhousekitchen.exception.WrongParamException;
import org.nexters.inhousekitchen.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.*;

@RestController
@Api(value="/", description="회원가입, 로그인, 로그아웃")

public class SignController {

	
	@Resource
	private MemberService memberService;
	
	
	
	@ApiOperation(httpMethod="POST", value="회원가입(로컬)")
	@RequestMapping(value="/signup", method=RequestMethod.POST, produces={ "text/plain; charset=utf-8" })
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error", response=String.class),
							   @ApiResponse(code = 400, message = "Bad Request", response=String.class),
		      				   @ApiResponse(code = 201, message = "Created", response=String.class)})
//	@ResponseBody
	public ResponseEntity<String> signin(@RequestBody(required=true) MemberDTO requestBody) throws ServerErrorException, WrongParamException{
		try {
			memberService.registerMember(requestBody, requestBody.getPrefer());
			System.out.println("done");
			return new ResponseEntity<String>("Created",HttpStatus.CREATED);
		}catch(ServerErrorException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage().toString(),HttpStatus.INTERNAL_SERVER_ERROR);
			
		}catch(WrongParamException e) {
			return new ResponseEntity<String>(e.getMessage().toString(),HttpStatus.BAD_REQUEST);
		}
	}
}
