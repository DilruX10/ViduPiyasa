package com.ds.courseservice.custom_exceptions;

public class MongoEntityNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
    public MongoEntityNotFoundException(String message) {
        super(message);
    }
    
}