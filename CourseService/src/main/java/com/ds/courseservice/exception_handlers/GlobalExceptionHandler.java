package com.ds.courseservice.exception_handlers;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import com.google.gson.Gson;

import com.ds.courseservice.custom_exceptions.*;
import comds.courseservice..models.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
  
	private Gson gson = new Gson();
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);    
	
    @ExceptionHandler(value = {MongoEntityNotFoundException.class, NoSuchElementException.class})
    protected ResponseEntity<Object> handleEntityNotFound(Exception ex) {	  
       
    	logger.warn("Entity not found exception caught: ", ex.getMessage(), ex);
    	
    	ApiError apiError = new ApiError("Entity Not Found", ex);
    	
	    String jsonResponse = gson.toJson(apiError);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonResponse);
    }
    
    
    @ExceptionHandler(value = {InvalidDocumentReferenceException.class})
    protected ResponseEntity<Object> handleInvalidDocumentReference(InvalidDocumentReferenceException ex) {	  
       
    	logger.warn("Invalid Document Reference exception caught: ", ex.getMessage(), ex);
    	
    	ApiError apiError = new ApiError("Session Overlap", ex.getValidationErrors());
    	
	    String jsonResponse = gson.toJson(apiError);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonResponse);
    }
    
    
    @ExceptionHandler(value = BadRequestException.class)
    protected ResponseEntity<Object> handleBadRequest(BadRequestException ex) {	  
       
    	logger.error("Bad request exception caught: ", ex.getMessage(), ex);
    	
    	ApiError apiError = new ApiError("Bad Request", ex);
	   
	    String jsonResponse = gson.toJson(apiError);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jsonResponse);
    }
    
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
          	
    	logger.warn("Method argument not valid exception caught: ", ex.getMessage(), ex);
        
    	ApiError apiError = new ApiError("Data Validation Errors", new ArrayList<>());
    	apiError.setValidationErrors(ex);
    	
    	String jsonResponse = gson.toJson(apiError);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonResponse);
    }
    
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<Object> handleMethodValidationExceptions(HandlerMethodValidationException ex) {

    	logger.warn("Method validation exception caught: ", ex.getMessage(), ex);
        
    	String errorMessage = ex.getAllErrors().stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ApiError apiError = new ApiError(errorMessage, ex);

        String jsonResponse = gson.toJson(apiError);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonResponse);
    }
    
    @ExceptionHandler(IllegalArgumentException.class)    
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
    	
    	logger.error("Illegal argument exception caught: ", ex.getMessage(), ex);
    	
    	ApiError apiError = new ApiError("Illegal Argument", ex);
 	   
	    String jsonResponse = gson.toJson(apiError);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonResponse);
    }
    
    @ExceptionHandler(Exception.class)
	 protected ResponseEntity<Object> handleGenericException(Exception ex) {
       
    	logger.error("Generic exception caught: ", ex.getMessage(), ex);
		
    	ApiError apiError = new ApiError(ex);  
       
	    String jsonResponse = gson.toJson(apiError);
	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(jsonResponse);
   }
}