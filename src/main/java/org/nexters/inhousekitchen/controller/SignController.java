package org.nexters.inhousekitchen.controller;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Resource;

import org.nexters.inhousekitchen.config.SessionEncoder;
import org.nexters.inhousekitchen.dto.DiningDTO;
import org.nexters.inhousekitchen.dto.MemberDTO;
import org.nexters.inhousekitchen.dto.MemberLoginDTO;
import org.nexters.inhousekitchen.exception.ServerErrorException;
import org.nexters.inhousekitchen.exception.WrongParamException;
import org.nexters.inhousekitchen.service.MemberService;
import org.nexters.inhousekitchen.validator.MemberValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.*;

@RestController
@Api(value="/api", description="회원가입, 로그인, 로그아웃")
@RequestMapping(value="/")
public class SignController {

	
	@Resource
	MemberValidator memberValidator;
	@Resource
	AuthenticationManager memberAuthManager;
	@Resource
	MemberService memberService;
	@Resource(name="passwordEncoder")
	BCryptPasswordEncoder passwordEncoder;
	@Resource
	ShaPasswordEncoder sessionEncoder;
	@Resource
	RedisTemplate<String, String> template;
	@Resource(name="redisTemplate")
	HashOperations<String, String, Authentication> hashOps;	
	@Resource
	String entryKey;
	
	
	
	
	/*--------------------로그아웃-----------------------*/
	@ApiOperation(httpMethod="GET", value="로그아웃")
	@RequestMapping(value="/signout", method=RequestMethod.GET, produces={ "text/plain; charset=utf-8" })
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error", response=String.class),
							   @ApiResponse(code = 400, message = "Bad Request", response=String.class),
		      				   @ApiResponse(code = 200, message = "OK", response=String.class)})
	public ResponseEntity<String> signup(){
		
		String username = null;
		Object principal = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth!=null) 
			principal = auth.getPrincipal();
		
	
			if(principal instanceof UserDetails) 
				username = ((UserDetails)principal).getUsername();
			else username = (String)principal;
			
			if(username.equals("anonymousUser")) 
				return new ResponseEntity<String>("이미 로그아웃 상태입니다.", HttpStatus.FORBIDDEN);
			else 
				return new ResponseEntity<String>("로그아웃 되었습니다.", HttpStatus.OK);
			
		
	}
	
	
	
	
	/*--------------------로그인-----------------------*/
	@ApiOperation(httpMethod="POST", value="로그인(로컬)")
	@RequestMapping(value="/signin", method=RequestMethod.POST, produces={ "text/plain; charset=utf-8" })
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error", response=String.class),
							   @ApiResponse(code = 400, message = "Bad Request", response=String.class),
		      				   @ApiResponse(code = 200, message = "OK", response=String.class)})
	public ResponseEntity<String> signup(@RequestBody(required=true) MemberLoginDTO requestBody, Errors errs){
		
		
		memberValidator.validate(requestBody, errs);
		if(errs.hasErrors()) {
			if(errs.getFieldError("pwd")!=null) 
				return new ResponseEntity<String>("비밀번호를 입력하세요.", HttpStatus.BAD_REQUEST);
			if(errs.getFieldError("userName")!=null) 
				return new ResponseEntity<String>("아이디를 입력하세요.", HttpStatus.BAD_REQUEST);
			
		}
		
		/*회원정보 있는지, 비밀번호 맞는지 검사*/
		Authentication token;
		Authentication result;
		try {
			token = new UsernamePasswordAuthenticationToken(requestBody.getUserName(), requestBody.getPwd());
			result = memberAuthManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(result);
			String authResult = sessionEncoder.encodePassword(requestBody.getUserName(), entryKey);
			hashOps.put(authResult, entryKey, result);
			return new ResponseEntity<String>(authResult, HttpStatus.OK);
			
		}catch(AuthenticationException e) {
			if(e.getMessage().toString()=="ServerError")
				return new ResponseEntity<String>("서버에러발생. 잠시 후 다시 시도해주세요.", HttpStatus.INTERNAL_SERVER_ERROR);
			else
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}
	
	
	
	/*--------------- 회원가입 ---------------------*/
	@ApiOperation(httpMethod="POST", value="회원가입(유효성 검사 모두 통과하고 요청되어야 합니다.)")
	@RequestMapping(value="/signup", method=RequestMethod.POST, produces={ "text/plain; charset=utf-8" })
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error", response=String.class),
							   @ApiResponse(code = 400, message = "Bad Request", response=String.class),
		      				   @ApiResponse(code = 201, message = "Created", response=String.class)})
	public ResponseEntity<String> signin(@Nonnull @RequestBody(required=true) MemberDTO requestBody, Errors errs) throws WrongParamException{
		try {
			memberValidator.validate(requestBody, errs);
			if(errs.hasErrors()) {
				if(errs.getFieldError("userName")!=null) 
					return new ResponseEntity<String>("아이디를 입력하세요.", HttpStatus.BAD_REQUEST);
				if(errs.getFieldError("pwd")!=null) 
					return new ResponseEntity<String>("비밀번호를 입력하세요.", HttpStatus.BAD_REQUEST);
				if(errs.getFieldError("email")!=null) 
					return new ResponseEntity<String>("이메일 주소를 입력하세요.", HttpStatus.BAD_REQUEST);
				if(errs.getFieldError("prefer")!=null) 
					return new ResponseEntity<String>("음식취향을 모두 입력하세요.", HttpStatus.BAD_REQUEST);		
			}
			
			/*비밀번호 암호화*/
			String encodedPwd = passwordEncoder.encode(requestBody.getPwd());
			requestBody.setPwd(encodedPwd);
		
			/*서비스계층 호출*/
			memberService.registerMember(requestBody, requestBody.getPrefer());
			return new ResponseEntity<String>("Created",HttpStatus.CREATED);
		
		}catch(ServerErrorException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage().toString(),HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
}
