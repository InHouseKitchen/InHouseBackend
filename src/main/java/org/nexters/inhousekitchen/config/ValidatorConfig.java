package org.nexters.inhousekitchen.config;

import org.nexters.inhousekitchen.validator.MemberValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidatorConfig {

	@Bean
	MemberValidator MemberValidator() {
		return new MemberValidator();
	}
	
	
}
