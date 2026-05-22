package com.hms.dto;

import java.time.LocalDate;

import com.hms.enums.BloodGroup;
import com.hms.enums.Gender;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class PatientUpdateRequestDto 
{
	    private String firstName;
	    private String lastName;
	    private String email;
	    private String phoneNumber;
	    private Gender gender;
	    private LocalDate dateOfBirth;
	    private BloodGroup bloodGroup;
	    private String maritalStatus;
	    private String occupation;	              
	    private Double height;	                     
	    private Double weight;
	    private String allergies;
	    private String emergencyContactName;
	    private String emergencyContactNumber;	   
	    private String insuranceProvider;	
	    private String insurancePolicyNumber;
	    private String address;	   
	    private String city;	
	    private String state;	
	    private String pincode;
	    @NotBlank(message = "Administrator name is required")
	    private String updatedBy;
}
