package org.nexters.inhousekitchen.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;



public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	/* 애플리케이션에서 security config 클래스 명시*/
	protected Class<?>[] getRootConfigClasses(){
		return new Class[] {
				WebSecurityConfig.class
		};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return null;
	}
}
