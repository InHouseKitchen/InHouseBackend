package org.nexters.inhousekitchen.security;

import java.io.IOException;
import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nexters.inhousekitchen.dto.MemberDTO;
import org.nexters.inhousekitchen.exception.ServerErrorException;
import org.nexters.inhousekitchen.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.GenericFilterBean;

public class CustomFilter extends GenericFilterBean{
	
	/* - 김다은 - 
	 * 중복되는 세션 검증코드를 줄이기 위해 필터 생성.
	 * TODO : swagger-ui에까지 적용되서 custom-filter 유알엘 제외하는 법 찾아야 함.
	 * 		   필터에서 검증된 MemberDTO 던지는 법도 알아봐야.
	 * */
	
	@Resource
	MemberService memberService;
	@Resource
	ShaPasswordEncoder sessionEncoder;
	@Resource
	RedisTemplate<String, String> template;
	@Resource(name="redisTemplate")
	HashOperations<String, String, Authentication> hashOps;	
	@Resource
	String entryKey;
	
	private MemberDTO member;
	
	
	MemberDTO getAuthorizedMember() {
		return member;
	}
	
	@Override
    public void doFilter(
      ServletRequest request, 
      ServletResponse response,
      FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
	
		String sessionID = httpRequest.getHeader("sessionid") == null ? "" : httpRequest.getHeader("sessionid");
	
		/*레디스에서 세션아이디로 토큰 가져오기*/
		Authentication auth = hashOps.get(sessionID, entryKey);
		Object principal = null;
		String username;
			
		if(auth!=null) 
			principal = auth.getPrincipal();
			
		if(principal instanceof UserDetails) 
			username = ((UserDetails)principal).getUsername();
		else username = (String)principal;
				
		
		if(username == null || username.equals("anonymousUser")) {
			httpResponse.sendError(401, "Unauthorized");
			return;
		}
		else {
			try {
				MemberDTO checkedMember = memberService.getMemberByUserName(username);
				if(checkedMember == null) {
					httpResponse.sendError(401, "Unauthorized");
					return;
				}
				chain.doFilter(request, response);
			} catch (ServerErrorException e) {
				e.printStackTrace();
				httpResponse.sendError(500, "Internal Server Error");
			}
		}
	
		
	}
        
}
