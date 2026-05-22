package com.hms.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.hms.enums.PaymentStatus;

import lombok.Data;

@Data
public class PaymentResponseDto 
{
    private Long paymentId;
    private String paymentReference;
    private String paymentMethod;
    private LocalDate paymentDate;
    private Double amountPaid;
    private PaymentStatus paymentStatus;
    private String transactionId;
    private Long billId;
    private String billNumber;
    private Double totalAmount;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}