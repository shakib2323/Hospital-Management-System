package com.hms.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class PharmacyStockRequestDto
{
	    @NotBlank(message = "Batch number is required!")
	    private String batchNumber;
	    @NotNull(message = "Quanitity available is required!")
	    private Integer quantityAvailable;
	    @NotNull(message = "Minimum stock level is required!")	 
	    private Integer minimumStockLevel;
	    @NotNull(message = "Maximum stock level is required!")
	    private Integer maximumStockLevel;
	    @NotNull(message = "Purchase date is required!")
	    private LocalDate purchaseDate;
	    @NotNull(message = "Expiry date is required!")
	    private LocalDate expiryDate;
	    @NotBlank(message = "Supplier name is required!")
	    private String supplierName;
	    @NotBlank(message = "Storage location is required!")
	    private String storageLocation;
	    @NotNull(message = "Medicine id is required!")
	    private Long medicineId; 
	    @NotBlank(message = "Administrator name is required")
	    private String createdBy;
	    private String updatedBy;
}
