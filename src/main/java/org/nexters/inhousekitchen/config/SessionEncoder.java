package org.nexters.inhousekitchen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

public class SessionEncoder {

	private ShaPasswordEncoder sessionEncoder;

	@Bean
	public ShaPasswordEncoder sessionEncoder() {
		this.sessionEncoder = new ShaPasswordEncoder(256);
		return this.sessionEncoder;
	}
	
	public String encodePassword(String arg0, Object arg1) {
		return sessionEncoder.encodePassword(arg0, arg1);
	}

	
	public boolean matches(String sid, String username, String key) {
		// TODO Auto-generated method stub
		return sessionEncoder.isPasswordValid(sid, username, key);
	}
	

}
