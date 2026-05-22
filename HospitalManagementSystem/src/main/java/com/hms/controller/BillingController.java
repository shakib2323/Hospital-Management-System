package com.hms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.dto.BillingResponseDto;
import com.hms.dto.BillingUpdateRequestDto;
import com.hms.response.ApiResponse;
import com.hms.service.BillingService;
import com.hms.service.PatientService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/billings")
@RequiredArgsConstructor
public class BillingController
{
  private final BillingService billingService;
  @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
  @PostMapping("/{patientId}/generate")
  public ResponseEntity<ApiResponse<BillingResponseDto>>generateBills(@PathVariable Long patientId)
  {
	  BillingResponseDto billing=billingService.generateBill(patientId);
	  ApiResponse<BillingResponseDto>response=ApiResponse.<BillingResponseDto>builder()
			  .success(true)
			  .message("Bill generated successfully")
			  .statusCode(201)
			  .data(billing)
			  .build();
	  return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
  @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
  @PatchMapping("/{billId}/update")
  public ResponseEntity<ApiResponse<BillingResponseDto>>updateBills(@PathVariable Long billId,@Valid @RequestBody BillingUpdateRequestDto dto)
  {
	  BillingResponseDto updateBill=billingService.updateBill(billId, dto);
	  ApiResponse<BillingResponseDto>response=ApiResponse.<BillingResponseDto>builder()
			  .success(true)
			  .message("Bill updated successfully")
			  .statusCode(200)
			  .data(updateBill)
			  .build();
	  return ResponseEntity.ok(response);
  }
  @GetMapping("/{billId}")
  public ResponseEntity<ApiResponse<BillingResponseDto>>getBillById(@PathVariable Long billId)
  {
	  BillingResponseDto getBill=billingService.getBillById(billId);
	  ApiResponse<BillingResponseDto>response=ApiResponse.<BillingResponseDto>builder()
			  .success(true)
			  .message("Bill founded successfully with id: "+billId)
			  .statusCode(200)
			  .data(getBill)
			  .build();
	  return ResponseEntity.ok(response);
  }
  @GetMapping("/")
  public ResponseEntity<ApiResponse<List<BillingResponseDto>>>getAllBills()
  {
	  List<BillingResponseDto> allBill=billingService.getAllBills();
	  ApiResponse<List<BillingResponseDto>>response=ApiResponse.<List<BillingResponseDto>>builder()
			  .success(true)
			  .message("All  Bills fetched successfully")
			  .statusCode(200)
			  .data(allBill)
			  .build();
	  return ResponseEntity.ok(response);
  }
  @GetMapping("/{pstientId}/calculate-bill")
  public ResponseEntity<ApiResponse<Double>>calculateTotalBills(@PathVariable Long patientId)
  {
	  Double allBill=billingService.calculateTotalBill(patientId);
	  ApiResponse<Double>response=ApiResponse.<Double>builder()
			  .success(true)
			  .message("Total bill calculated")
			  .statusCode(200)
			  .data(allBill)
			  .build();
	  return ResponseEntity.ok(response);
  }
  @GetMapping("/{billId}/{discountPrecentage}/discount")
  public ResponseEntity<ApiResponse<String>>applyDiscounts(@PathVariable Long billId,@PathVariable Double discountPercentage)
  {
	  billingService.applyDiscount(billId, discountPercentage);
	  ApiResponse<String>response=ApiResponse.<String>builder()
			  .success(true)
			  .message("Discount applied")
			  .statusCode(200)
			  .data(null)
			  .build();
	  return ResponseEntity.ok(response);
  }
  @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
  @PatchMapping("/{billId}/cancel")
  public ResponseEntity<ApiResponse<String>>cancelBills(@PathVariable Long billId)
  {
	  billingService.cancelBill(billId);
	  ApiResponse<String>response=ApiResponse.<String>builder()
			  .success(true)
			  .message("Bill canceled")
			  .statusCode(200)
			  .data(null)
			  .build();
	  return ResponseEntity.ok(response);
  }
  
}
