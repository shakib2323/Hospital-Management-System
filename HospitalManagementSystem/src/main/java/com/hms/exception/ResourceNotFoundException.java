package com.hms.exception;

public class ResourceNotFoundException extends RuntimeException
{
	private String error;
    public ResourceNotFoundException(String message)
    {
        super(message);
        this.error=error;
    }
}
