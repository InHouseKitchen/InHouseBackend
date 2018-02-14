package org.nexters.inhousekitchen.controller;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;


import org.nexters.inhousekitchen.dto.MemberEmailDTO;
import org.nexters.inhousekitchen.dto.MemberPasswordDTO;
import org.nexters.inhousekitchen.dto.MemberUserNameDTO;
import org.nexters.inhousekitchen.validator.MemberValidator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.*;


@RestController
@RequestMapping("/member")
@Api(value="/member", description="회원정보 유효성 검사")
@ApiResponses(value = { 
			@ApiResponse(code=500, message="Internal Server Error", response=String.class),
		   @ApiResponse(code=200, message="OK", response=String.class),
		   @ApiResponse(code=400, message="Bad Request", response=String.class)})


public class MemberController {
	

	@Resource
	MemberValidator memberValidator;

	
	private static final Map<String, String> CATEGORY = new HashMap<String, String>(){{
		put("email", "이메일");
		put("userName", "아이디");
		put("pwd", "비밀번호");
	}}; 
	
	/*회원가입 시 입력정보 유효성 검사*/
	/* Validator는 바디로 들어오는 데이터필드를 가진 객체만 검증한다. 
	 *  바디로 받으려면 메소드는 POST로.*/
	
	
	
	
	/*이메일주소 유효성 검사*/
	@ApiOperation(httpMethod="POST", value="이메일 검사")
	@RequestMapping(value="/email", method=RequestMethod.POST, consumes= {"application/json"}, produces={ "text/plain;charset=utf-8" })
	public ResponseEntity<String> checkEmail(@RequestBody MemberEmailDTO email, Errors errs) {
		memberValidator.validate(email, errs);		
		return getResponseEntity(errs, "email");
	}		
	
	
	
	
	/*아이디 유효성 검사*/
	@ApiOperation(httpMethod="POST", value="아이디 검사")
	@RequestMapping(value="/username", method=RequestMethod.POST, consumes= {"application/json"}, produces={ "text/plain;charset=utf-8" })
	public ResponseEntity<String> checkUsername(@RequestBody MemberUserNameDTO username, Errors errs) {
		
		memberValidator.validate(username, errs);
		return getResponseEntity(errs, "userName");
	}		
	
	
	
	
	/*비밀번호 유효성 검사*/
	@ApiOperation(httpMethod="POST", value="비밀번호 검사")
	@RequestMapping(value="/password", method=RequestMethod.POST, consumes= {"application/json"}, produces={ "text/plain;charset=utf-8" })
	public ResponseEntity<String> checkPassword(@RequestBody MemberPasswordDTO password, Errors errs) {
		
		memberValidator.validate(password, errs);
		return getResponseEntity(errs, "pwd");
	}		
	
	
	
	
	/*예외별로 응답객체 결정하는 함수*/
	public static ResponseEntity<String> getResponseEntity(Errors errs, String category){
		String responseMessage = "사용가능한 "+CATEGORY.get(category)+"입니다.";
		HttpStatus statusCode = HttpStatus.OK;
		if(errs.hasErrors()) {
			switch(errs.getFieldError(category).getCode()) {
				case "Null" : responseMessage = CATEGORY.get(category)+"을/를 입력하세요."; statusCode = HttpStatus.BAD_REQUEST; break;
				case "WrongExpression" : responseMessage = CATEGORY.get(category)+"형식이 올바르지 않습니다."; statusCode = HttpStatus.BAD_REQUEST; break;
				case "Duplicated" : responseMessage = "이미 존재하는 "+CATEGORY.get(category)+"입니다."; statusCode = HttpStatus.BAD_REQUEST; break;
				case "ServerError" : responseMessage = "서버에러발생. 잠시 후 다시 시도해주세요."; statusCode = HttpStatus.INTERNAL_SERVER_ERROR; 
			}
		}
		return new ResponseEntity<String>(responseMessage, statusCode);
	}
}
