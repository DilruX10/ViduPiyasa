package com.ds.courseservice.custom_exceptions;

import java.util.List;

import com.sliit.af.timetable_management_system.models.ApiValidationError;

public class InvalidDocumentReferenceException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private List<ApiValidationError> validationErrors;

    public InvalidDocumentReferenceException(List<ApiValidationError> validationErrors) {
        super("Invalid document references");
        this.validationErrors = validationErrors;
    }

    public List<ApiValidationError> getValidationErrors() {
        return validationErrors;
    }
}
