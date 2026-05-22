package com.hms.dto;

import java.time.LocalDate;

import com.hms.enums.BillStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class BillingUpdateRequestDto 
{
    @NotNull(message = "Consultation charges is required!")
    @Positive(message = "Consultation charges must be positive!")
    private Double consultationCharges;
    @NotNull(message = "Medicine charges is required!")
    @Positive(message = "Medicine charges must be positive!")
    private Double medicineCharges;
    @NotNull(message = "Lab charges is required!")
    @Positive(message = "Lab charges must be positive!")
    private Double labCharges;
    @NotNull(message = "Room charges is required!")
    @Positive(message = "Room charges must be positive!")
    private Double roomCharges;
    @NotNull(message = "Discount amount is required!")
    private Double discountAmount;
    @NotNull(message = "Tax amount is required!")
    private Double taxAmount;
    @NotNull(message = "Total amount is required!")
    @Positive(message = "Total amount must be positive!")
    private Double totalAmount;
    @NotNull(message = "Bill status is required!")
    private BillStatus billStatus;
    @NotNull(message = "Generated date is required!")
    private LocalDate generatedDate;
    @NotNull(message = "Due date is required!")
    private LocalDate dueDate;
    @NotBlank(message = "Administrator name is required.")
    private String updatedBy;
}