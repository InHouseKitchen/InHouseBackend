package org.nexters.inhousekitchen.controller;


import javax.annotation.Nonnull;
import javax.annotation.Resource;

import org.nexters.inhousekitchen.dto.MemberDTO;

import org.nexters.inhousekitchen.exception.ServerErrorException;
import org.nexters.inhousekitchen.exception.WrongParamException;
import org.nexters.inhousekitchen.service.MemberService;
import org.nexters.inhousekitchen.validator.MemberValidator;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.*;

@RestController
@Api(value="/", description="회원가입, 로그인, 로그아웃")

public class SignController {

	private final static String SESSION_KEY = "kitchen:sid235092-149023";
	
	@Resource
	MemberValidator memberValidator;
	@Resource
	private MemberService memberService;
	@Resource
	private BCryptPasswordEncoder passwordEncoder;
	@Resource
	private RedisTemplate<String, String> template;
	@Resource(name="redisTemplate")
	private HashOperations<String, String, String> hashOps;
		
	
	/*로그인*/
	@ApiOperation(httpMethod="POST", value="로그인(로컬)")
	@RequestMapping(value="/signin", method=RequestMethod.POST, produces={ "text/plain; charset=utf-8" })
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error", response=String.class),
							   @ApiResponse(code = 400, message = "Bad Request", response=String.class),
		      				   @ApiResponse(code = 200, message = "OK", response=String.class)})
	public ResponseEntity<String> signup(@RequestBody(required=false) MemberDTO requestBody, Errors errs){
		hashOps.put(SESSION_KEY,"userId+SESSION_KEY", "userId+username");
		System.out.println("session created");
		System.out.println("inserted valeu : "+hashOps.get(SESSION_KEY, "userId+SESSION_KEY"));
		return new ResponseEntity<String>("session created", HttpStatus.OK);
	}
	
	
	
	@ApiOperation(httpMethod="POST", value="회원가입(유효성 검사 모두 통과하고 요청되어야 합니다.)")
	@RequestMapping(value="/signup", method=RequestMethod.POST, produces={ "text/plain; charset=utf-8" })
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error", response=String.class),
							   @ApiResponse(code = 400, message = "Bad Request", response=String.class),
		      				   @ApiResponse(code = 201, message = "Created", response=String.class)})
	public ResponseEntity<String> signin(@Nonnull @RequestBody(required=true) MemberDTO requestBody, Errors errs) 
			throws ServerErrorException{
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
			
		}catch(WrongParamException e) {
			return new ResponseEntity<String>(e.getMessage().toString(),HttpStatus.BAD_REQUEST);
		}
	}
}
