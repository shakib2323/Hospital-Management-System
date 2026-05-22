package com.hms.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SpecializationRequestDto 
{
	@NotBlank(message = "Specialization name is required!")
	private String specializationName;	
	@NotBlank(message = "Description name is required!")
	private String description;
	@NotBlank(message = "Administrator name is required!")
	private String createdBy;
	private String updatedBy;
}
