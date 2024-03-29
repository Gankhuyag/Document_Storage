package com.documents.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DocNotFoundException extends RuntimeException {

	public DocNotFoundException(String message) {
		super(message); 
	}	
	
}
