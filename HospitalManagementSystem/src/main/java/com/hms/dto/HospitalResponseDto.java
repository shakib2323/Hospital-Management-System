package com.hms.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class HospitalResponseDto 
{
	private Long hospitalId;
	private String hospitalName;
	private String hospitalCode;
	private String email;
	private String phoneNumber;
	private String website;
	private String registrationNumber;
	private Integer establishedYear;
	private String description;
	private Boolean active;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private String createdBy;
	private String updatedBy;
	private List<BranchResponseDto> branches;
}
