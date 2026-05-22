package com.hms.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.hms.dto.PaymentRequestDto;
import com.hms.dto.PaymentResponseDto;
import com.hms.entity.Billing;
import com.hms.entity.Payment;
import com.hms.exception.ResourceNotFoundException;
import com.hms.repository.BillingRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PaymentMapper 
{
    private final BillingRepository billingRepo;
    public Payment toEntity(PaymentRequestDto dto) 
    {
        Payment payment = new Payment();
        payment.setPaymentReference(dto.getPaymentReference());
        payment.setPaymentMethod(dto.getPaymentMethod());
        payment.setPaymentDate(dto.getPaymentDate());
        payment.setAmountPaid(dto.getAmountPaid());
        payment.setPaymentStatus(dto.getPaymentStatus());
        payment.setTransactionId(dto.getTransactionId());
        Billing billing = billingRepo.findById(dto.getBillId())
                .orElseThrow(() -> new ResourceNotFoundException("Bill not found with id: " + dto.getBillId()));
        payment.setBilling(billing);
        payment.setCreatedBy(dto.getCreatedBy());
        payment.setUpdatedBy(dto.getUpdatedBy());
        return payment;
    }

    public PaymentResponseDto toDto(Payment payment) 
    {
        PaymentResponseDto dto = new PaymentResponseDto();
        dto.setPaymentId(payment.getPaymentId());
        dto.setPaymentReference(payment.getPaymentReference());
        dto.setPaymentMethod(payment.getPaymentMethod());
        dto.setPaymentDate(payment.getPaymentDate());
        dto.setAmountPaid(payment.getAmountPaid());
        dto.setPaymentStatus(payment.getPaymentStatus());
        dto.setTransactionId(payment.getTransactionId());
        if (payment.getBilling() != null) {
            dto.setBillId(payment.getBilling().getBillId());
            dto.setBillNumber(payment.getBilling().getBillNumber());
            dto.setTotalAmount(payment.getBilling().getTotalAmount());
        }
        dto.setCreatedBy(payment.getCreatedBy());
        dto.setUpdatedBy(payment.getUpdatedBy());
        dto.setCreatedAt(payment.getCreatedAt());
        dto.setUpdatedAt(payment.getUpdatedAt());
        return dto;
    }

    public List<PaymentResponseDto> toDtoList(List<Payment> payments) 
    {
        return payments.stream().map(this::toDto).collect(Collectors.toList());
    }
}