package com.hms.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;
@Data
public class MedicineResponseDto 
{
	private Long medicineId;
	private String medicineName;
	private String medicineCode;
	private String manufacturer;
	private String description;
	private String dosageForm;
	private String strength;
	private Double unitPrice;
	private LocalDate expiryDate;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private String createdBy;
	private String updatedBy;
}
