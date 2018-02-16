package org.nexters.inhousekitchen.config;

import org.nexters.inhousekitchen.controller.MemberAuthManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AuthConfig {
	 
	@Bean
	 AuthenticationManager memberAuthManager() {
		  return new MemberAuthManager();
	 }

	 @Bean
	 BCryptPasswordEncoder passwordEncoder() {
		  return new BCryptPasswordEncoder();
	 }
}
