package com.hms.service;

import java.util.List;

import com.hms.dto.PharmacyStockRequestDto;
import com.hms.dto.PharmacyStockResponseDto;
import com.hms.dto.PharmacyStockUpdateRequestDto;

public interface PharmacyStockService 
{
   public PharmacyStockResponseDto addStock(PharmacyStockRequestDto dto);
   public PharmacyStockResponseDto updateStock(Long stockId,PharmacyStockUpdateRequestDto  dto);
   public PharmacyStockResponseDto getStockById(Long stockId);
   public List<PharmacyStockResponseDto>getLowStockMedicines();
   public void reduceMedicineStock(Long medicineId,Integer quantity);
   public void deleteStock(Long stockId);
}
