package com.hms.exception;

import java.util.Map;

public class InvalidOperationException extends RuntimeException
{
	 private Map<String, String> errors;
     
	    public InvalidOperationException(Map<String, String> errors) {
	        this.errors = errors;
	    }

	    public Map<String, String> getErrors() {
	        return errors;
	    }
	  
}
