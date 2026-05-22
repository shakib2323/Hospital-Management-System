package com.hms.dto;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class SpecializationResponseDto 
{
	private String specializationName;
	private String description;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private String createdBy;
	private String updatedBy;
}
