package com.hms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hms.dto.PaymentRequestDto;
import com.hms.dto.PaymentResponseDto;
import com.hms.entity.Billing;
import com.hms.entity.Payment;
import com.hms.enums.BillStatus;
import com.hms.enums.PaymentStatus;
import com.hms.exception.ResourceNotFoundException;
import com.hms.mapper.PaymentMapper;
import com.hms.repository.BillingRepository;
import com.hms.repository.PaymentRepository;
import com.hms.service.PaymentService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService
{
   private final PaymentRepository paymentRepo;
   private final PaymentMapper paymentMapper;
   private final BillingRepository billingRepo;
   
	@Override
	public PaymentResponseDto processPayment(PaymentRequestDto dto) {
		Payment payment=paymentMapper.toEntity(dto);
		paymentRepo.save(payment);
		return paymentMapper.toDto(payment);
	}

	@Override
	public PaymentResponseDto getPaymentById(Long paymentId) {
		Payment payment=paymentRepo.findById(paymentId).orElseThrow(()->
		new ResourceNotFoundException("Payment not found with id: "+paymentId));
		return paymentMapper.toDto(payment);
	}

	@Override
	public List<PaymentResponseDto> getAllPayments() {
		List<Payment>payment=paymentRepo.findAll();
		return paymentMapper.toDtoList(payment);
	}

	@Override
	public PaymentResponseDto refundPayment(Long paymentId) {
		Payment payment=paymentRepo.findById(paymentId).orElseThrow(()->
		new ResourceNotFoundException("Payment not found with id: "+paymentId));
		  //Check if payment is already refunded
	    if (payment.getPaymentStatus().equals(PaymentStatus.REFUNDED)) {
	        throw new IllegalStateException("Payment is already refunded with id: " + paymentId);
	    }
	    //Check if payment is completed — only completed payments can be refunded
	    if (!payment.getPaymentStatus().equals(PaymentStatus.COMPLETED)) {
	        throw new IllegalStateException("Only completed payments can be refunded!");
	    }
	    payment.setPaymentStatus(PaymentStatus.REFUNDED);
	    //Update billing status to REFUNDED
	    Billing billing = payment.getBilling();
	    billing.setBillStatus(BillStatus.REFUNDED);
	    billingRepo.save(billing);
	    paymentRepo.save(payment);
	    return paymentMapper.toDto(payment);
	
	}

	@Override
	public Boolean verifyPaymentStatus(String transactionId) {
	    //Find payment by transactionId
	    Payment payment = paymentRepo.findByTransactionId(transactionId)
	            .orElseThrow(() -> new ResourceNotFoundException("Payment not found with transaction id: " + transactionId));
	    //Return true if payment is COMPLETED
	    return payment.getPaymentStatus().equals(PaymentStatus.COMPLETED);
	}

}
