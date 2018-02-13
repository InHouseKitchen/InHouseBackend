package org.nexters.inhousekitchen.controller;

import java.util.regex.Pattern;

import org.nexters.inhousekitchen.dto.MemberDTO;
import org.springframework.validation.*;

public class MemberValidator implements Validator{

	@Override
	public boolean supports(Class<?> arr0) {
		return MemberDTO.class.isAssignableFrom(arr0);
	}
	
	@Override
	public void validate(Object obj, Errors errs) {
		System.out.println("-----validate() called-----");
		
		/*이메일 정규식*/
		Pattern emailRegExp = Pattern.compile("^[0-9a-zA-Z]([\\-.\\w]*[0-9a-zA-Z\\-_+])*@([0-9a-zA-Z][\\-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9}$");
		/*영문 대소문자, 숫자, 특수문자 혼합해서 8~16자리 이내*/
		Pattern pwdRegExp = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,16}$");
		
		
		MemberDTO mem = (MemberDTO) obj;
		String email = mem.getEmail();
		String password = mem.getPwd();
		
		if(email==null || !emailRegExp.matcher(email).find()) 
			errs.rejectValue("email", "이메일");
		
		if(password==null || !pwdRegExp.matcher(password).find()) 
			errs.rejectValue("pwd", "비밀번호");
			
	}
}
