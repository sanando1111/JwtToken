package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.LOOP_DETECTED)
public class CustomException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 697276680134188412L;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public CustomException(String message) {
		super("Reason:"+message);
		this.message = message;
	}
	

}
