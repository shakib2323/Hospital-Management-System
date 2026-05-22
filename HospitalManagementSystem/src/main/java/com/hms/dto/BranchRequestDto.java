package com.hms.dto;

import org.springframework.format.annotation.NumberFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BranchRequestDto 
{
	@NotBlank(message = "Branch name is required")
	private String branchName;
	@NotBlank(message = "Branch code is required")
    private String branchCode;
	@NotBlank(message = "Branch city is required")
    private String city;
	@NotBlank(message = "Branch state is required")
    private String state;
	@NotBlank(message = "Branch country is required")
    private String country;
	@NotBlank(message = "Branch pincode is required")
    private String pincode;
	@NotBlank(message = "Branch address is required")
    private String address;
	@NotBlank(message = "Branch contect number is required")
    private String contactNumber;
	@NotBlank(message = "Branch emergency contect number is required")
    private String emergencyContact;
	@NotNull(message = "latitude is required")
	@NumberFormat
    private Double latitude;
	@NotNull(message = "longitude is required")
	@NumberFormat
    private Double longitude;
	@NotBlank(message = "Administrator name is required.")
    private String createdBy;
    private String updatedBy;
}
