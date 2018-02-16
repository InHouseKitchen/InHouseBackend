package org.nexters.inhousekitchen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class ServerErrorException extends Exception{

	public ServerErrorException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
