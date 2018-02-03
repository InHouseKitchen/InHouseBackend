package org.nexters.inhousekitchen.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.nexters.inhousekitchen.dto.HostDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping("/")
@Api(value="swag-controller")
public class HomeController {
	
	
	@ResponseBody
	public String home(){
		System.out.println("보빈짱");
		return "home";
	}
	
	@RequestMapping(value= "/ajax.seo", method=RequestMethod.GET)
	@ResponseBody
	public String person(@RequestParam("id") String id,HttpServletResponse response)  {
	    String personJson = "{\"id\":\""+"김다은"
	                    +"\",\"name\":\""+"자고싶다"
	                    +"\",\"password\":\""+"password"
	                    +"\",\"email\":\""+"getting sleep"+"\"}";
	
	   return personJson;
	}
	
}