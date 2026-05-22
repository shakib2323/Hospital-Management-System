package com.hms.exception;



public class HospitalAlreadyActiveException extends RuntimeException
{
  private String error;
  public HospitalAlreadyActiveException(String error)
  {
	    super(error); 
	    this.error = error;
	}
}
