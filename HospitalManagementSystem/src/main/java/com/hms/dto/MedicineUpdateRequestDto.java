package com.hms.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class MedicineUpdateRequestDto 
{
	private String medicineName;
	private String manufacturer;
	private String description;
	private String dosageForm;
	private String strength;
	private LocalDate expiryDate;
	@NotBlank(message = "Administrator name is required")
	private String updatedBy;
}
