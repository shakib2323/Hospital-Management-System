package com.hms.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BranchResponseDto 
{
	private Long branchId;
    private String branchName;
    private String branchCode;
    private String city;
    private String state;
    private String country;
    private String pincode;
    private String address;
    private String contactNumber;
    private String emergencyContact;
    private Double latitude;
    private Double longitude;
    private Long Hospital;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private String createdBy;
	private String updatedBy;
}
