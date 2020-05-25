package com.own.exeptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.MalformedURLException;

@SuppressWarnings("serial")
@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class MyFileNotFoundException extends Exception {
	private final String message;

	public MyFileNotFoundException(String message) {
		this.message=message;
	}

	public MyFileNotFoundException(String message, MalformedURLException ex) {
		super(ex);
		this.message=message;
	}

}
