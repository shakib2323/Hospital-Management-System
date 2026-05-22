package com.hms.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;
@Data
public class PharmacyStockResponseDto 
{
	    private Long stockId;	   
	    private String batchNumber;	
	    private Integer quantityAvailable;
	    private Integer minimumStockLevel;	
	    private Integer maximumStockLevel;
	    private LocalDate purchaseDate;
	    private LocalDate expiryDate;	  
	    private String supplierName;
	    private String storageLocation;	
	    private Long medicineId;	
	    private String medicineName;
	    private LocalDateTime createdAt;
	    private LocalDateTime updatedAt;
	    private String createdBy;
	    private String updatedBy;
}
