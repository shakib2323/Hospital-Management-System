package com.hms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.dto.PaymentRequestDto;
import com.hms.dto.PaymentResponseDto;
import com.hms.response.ApiResponse;
import com.hms.service.PaymentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payments")
public class PaymentController {
  private final PaymentService paymentService;
  @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
  @PostMapping("/process-payment")
  public ResponseEntity<ApiResponse<PaymentResponseDto>> processPayment(@Valid @RequestBody PaymentRequestDto dto)
  {
	  PaymentResponseDto payment=paymentService.processPayment(dto);
	  ApiResponse<PaymentResponseDto>response=ApiResponse.<PaymentResponseDto>builder()
			  .success(true)
			  .statusCode(201)
			  .message("Payment proceed successfully")
			  .data(payment)
			  .build();
	  return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
  @GetMapping("/{paymentId}")
  public ResponseEntity<ApiResponse<PaymentResponseDto>> findPaymentById(@PathVariable Long paymentId)
  {
	  PaymentResponseDto payment=paymentService.getPaymentById(paymentId);
	  ApiResponse<PaymentResponseDto>response=ApiResponse.<PaymentResponseDto>builder()
			  .success(true)
			  .message("Payment fetched successfully")
			  .statusCode(200)
			  .data(payment)
			  .build();
	  return ResponseEntity.ok(response);
  }
  @GetMapping("/")
  public ResponseEntity<ApiResponse<List<PaymentResponseDto>>> findAllPayments()
  {
	  List<PaymentResponseDto> payment=paymentService.getAllPayments();
	  ApiResponse<List<PaymentResponseDto>>response=ApiResponse.<List<PaymentResponseDto>>builder()
			  .success(true)
			  .message("All Payment fetched successfully")
			  .statusCode(200)
			  .data(payment)
			  .build();
	  return ResponseEntity.ok(response);
  }
  @GetMapping("/{paymentId}/refund-payment")
  public ResponseEntity<ApiResponse<PaymentResponseDto>> refundPayments(@PathVariable Long paymentId)
  {
	  PaymentResponseDto payment=paymentService.refundPayment(paymentId);
	  ApiResponse<PaymentResponseDto>response=ApiResponse.<PaymentResponseDto>builder()
			  .success(true)
			  .message("Payment refund successfully")
			  .statusCode(200)
			  .data(payment)
			  .build();
	  return ResponseEntity.ok(response);
  }
  @GetMapping("/{transactionId}/verify")
  public ResponseEntity<ApiResponse<String>> verifyPaymentStatus(@PathVariable String transactionId)
  {
	paymentService.verifyPaymentStatus(transactionId);
	  ApiResponse<String>response=ApiResponse.<String>builder()
			  .success(true)
			  .message("Payment verifyed successfully")
			  .statusCode(200)
			  .data(null)
			  .build();
	  return ResponseEntity.ok(response);
  }
}
