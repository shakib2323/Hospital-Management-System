package com.hms.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PharmacyStockUpdateRequestDto
{
    @NotNull(message = "Quantity available is required!")
    @Positive(message = "Quantity must be positive!")
    private Integer quantityAvailable;
    @NotNull(message = "Minimum stock level is required!")
    @Positive(message = "Minimum stock level must be positive!")
    private Integer minimumStockLevel;
    @NotNull(message = "Maximum stock level is required!")
    @Positive(message = "Maximum stock level must be positive!")
    private Integer maximumStockLevel;
    @NotNull(message = "Expiry date is required!")
    private LocalDate expiryDate;
    @NotBlank(message = "Supplier name is required!")
    private String supplierName;
    @NotBlank(message = "Storage location is required!")
    private String storageLocation;
    @NotBlank(message = "Administrator name is required")
    private String updatedBy;

 
  
}