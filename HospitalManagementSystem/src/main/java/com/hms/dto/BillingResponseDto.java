package com.hms.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.hms.enums.BillStatus;

import lombok.Data;

@Data
public class BillingResponseDto 
{
    private Long billId;
    private String billNumber;
    private Double consultationCharges;
    private Double medicineCharges;
    private Double labCharges;
    private Double roomCharges;
    private Double discountAmount;
    private Double taxAmount;
    private Double totalAmount;
    private BillStatus billStatus;
    private LocalDate generatedDate;
    private LocalDate dueDate;
    private Long patientId;
    private String patientName;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}