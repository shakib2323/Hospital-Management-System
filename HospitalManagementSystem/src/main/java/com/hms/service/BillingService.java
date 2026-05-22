package com.hms.service;

import java.util.List;

import com.hms.dto.BillingResponseDto;
import com.hms.dto.BillingUpdateRequestDto;

public interface BillingService 
 {
	public BillingResponseDto generateBill(Long patientId);
	public BillingResponseDto updateBill(Long billId,BillingUpdateRequestDto dto);
	public BillingResponseDto getBillById(Long billId);
	public List<BillingResponseDto> getAllBills();
	public Double calculateTotalBill(Long patientId);
	public void applyDiscount(Long billId, Double discountPercentage);
	public void cancelBill(Long billId);
 }
