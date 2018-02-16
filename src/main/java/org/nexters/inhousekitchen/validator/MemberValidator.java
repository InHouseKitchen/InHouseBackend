package org.nexters.inhousekitchen.validator;

import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.nexters.inhousekitchen.dto.MemberDTO;
import org.nexters.inhousekitchen.dto.MemberEmailDTO;
import org.nexters.inhousekitchen.dto.MemberLoginDTO;
import org.nexters.inhousekitchen.dto.MemberPasswordDTO;
import org.nexters.inhousekitchen.dto.MemberUserNameDTO;
import org.nexters.inhousekitchen.exception.ServerErrorException;
import org.nexters.inhousekitchen.service.MemberService;

import org.springframework.validation.*;


public class MemberValidator implements Validator{

	/*이메일 정규식*/
	private final static String EMAIL_REGEXP = "^[0-9a-zA-Z]([\\-.\\w]*[0-9a-zA-Z\\-_+])*@([0-9a-zA-Z][\\-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9}$";
	/*비밀번호 정규식(영문 대소문자, 숫자, 특수문자 혼합해서 8~16자리 이내)*/
	private final static String PASSWORD_REGEXP = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,16}$";

	
	@Resource
	MemberService memberService;
	
	
	@Override
	public boolean supports(Class<?> arr0) {
		return MemberDTO.class.isAssignableFrom(arr0);
	}
	
	@Override
	public void validate(Object obj, Errors errs) {
		MemberDTO mem = (MemberDTO) obj;
		MemberDTO duplicateMem;
		Pattern emailPattern;
		Pattern pwdPattern;
		
		/*이메일 검증*/
		if(obj instanceof MemberEmailDTO) {
			String email = mem.getEmail();
			emailPattern = Pattern.compile(EMAIL_REGEXP);
			if(!emailPattern.matcher(email).find()) 
				errs.rejectValue("email", "WrongExpression");
			try {				
				duplicateMem = memberService.getMemberByEmail(mem.getEmail());
				if(duplicateMem!=null) errs.rejectValue("email", "Duplicated");
			}catch(ServerErrorException e) {
				e.printStackTrace();
				errs.rejectValue("email", "ServerError");
			}
			return;
		}

		/*비밀번호 검증*/
		if(obj instanceof MemberPasswordDTO) {
			pwdPattern = Pattern.compile(PASSWORD_REGEXP);
			if(!pwdPattern.matcher(mem.getPwd()).find()) 
				errs.rejectValue("pwd", "WrongExpression");
			return;
		}
		
		/*아이디 검증*/
		if(obj instanceof MemberUserNameDTO) {
			try {				
				duplicateMem = memberService.getMemberByUserName(mem.getUserName());
				if(duplicateMem!=null) errs.rejectValue("userName", "Duplicated");
			}catch(ServerErrorException e) {
				e.printStackTrace();
				errs.rejectValue("userName", "ServerError");
			}
			return;
		}
		
		
		
		/*회원가입 시 null 검증*/
		/* TODO : if문이 너무 많음. 프로퍼티 널 체크 간결하게 하는 법 모색*/
		if(obj instanceof MemberDTO) {
			if(mem.getUserName()==null || mem.getUserName().trim().isEmpty())
				errs.rejectValue("userName", "Null");
			if(mem.getPwd()==null || mem.getPwd().trim().isEmpty())
				errs.rejectValue("pwd", "Null");
			if(mem.getEmail()==null || mem.getEmail().trim().isEmpty())
				errs.rejectValue("email", "Null");
			if(mem.getPrefer()!=null) {
				if(mem.getPrefer().getFavor1()==null || mem.getPrefer().getFavor1().trim().isEmpty())
					errs.rejectValue("prefer", "Null");
				if(mem.getPrefer().getFavor2()==null || mem.getPrefer().getFavor2().trim().isEmpty())
					errs.rejectValue("prefer", "Null");
				if(mem.getPrefer().getFavor3()==null || mem.getPrefer().getFavor3().trim().isEmpty())
					errs.rejectValue("prefer", "Null");
				if(mem.getPrefer().getFavor4()==null || mem.getPrefer().getFavor4().trim().isEmpty())
					errs.rejectValue("prefer", "Null");
			}
		}
			
	}
}
