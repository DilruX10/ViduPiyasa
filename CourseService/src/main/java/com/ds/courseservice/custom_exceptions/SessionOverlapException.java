package com.ds.courseservice.custom_exceptions;

public class SessionOverlapException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
    public SessionOverlapException(String message) {
        super(message);
    }
    
}
