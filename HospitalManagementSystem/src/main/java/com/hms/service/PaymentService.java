package com.hms.service;

import java.util.List;

import com.hms.dto.PaymentRequestDto;
import com.hms.dto.PaymentResponseDto;

public interface PaymentService 
{
	public PaymentResponseDto processPayment(PaymentRequestDto dto);
	public PaymentResponseDto getPaymentById(Long paymentId);
	public List<PaymentResponseDto> getAllPayments();
	public PaymentResponseDto refundPayment(Long paymentId);
	public Boolean verifyPaymentStatus(String transactionId);
}