package com.hms.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class MedicineRequestDto 
{
    @NotBlank(message = "Medicine name is required!")
	private String medicineName;
    @NotBlank(message = "Medicine code is required!")
	private String medicineCode;
    @NotBlank(message = "Manufacturer is required!")
	private String manufacturer;
    @NotBlank(message = "Description is required!")
	private String description;
    @NotBlank(message = "Dosage form is required!")
	private String dosageForm;
    @NotBlank(message = "Strength is required!")
	private String strength;
    @NotNull(message = "Unit price is required!")
	private Double unitPrice;
    @NotNull(message = "Expiry date is required")
	private LocalDate expiryDate;
    @NotBlank(message = "Administrator name is required")
	private String createdBy;
	private String updatedBy;
}
