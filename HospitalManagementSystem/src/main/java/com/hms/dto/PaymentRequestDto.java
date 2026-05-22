package com.hms.dto;

import java.time.LocalDate;

import com.hms.enums.PaymentStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PaymentRequestDto 
{
    @NotBlank(message = "Payment reference is required!")
    private String paymentReference;
    @NotBlank(message = "Payment method is required!")
    private String paymentMethod;
    @NotNull(message = "Payment date is required!")
    private LocalDate paymentDate;
    @NotNull(message = "Amount paid is required!")
    @Positive(message = "Amount paid must be positive!")
    private Double amountPaid;
    @NotNull(message = "Payment status is required!")
    private PaymentStatus paymentStatus;
    @NotBlank(message = "Transaction id is required!")
    private String transactionId;
    @NotNull(message = "Bill id is required!")
    private Long billId;
    @NotBlank(message = "Administrator name is required")
    private String createdBy;
    private String updatedBy;
}