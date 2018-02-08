package org.nexters.inhousekitchen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ServerErrorException extends CustomException{

	public ServerErrorException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
