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

import com.hms.dto.PharmacyStockRequestDto;
import com.hms.dto.PharmacyStockResponseDto;
import com.hms.dto.PharmacyStockUpdateRequestDto;
import com.hms.response.ApiResponse;
import com.hms.service.PharmacyStockService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/pharmacystocks")
@RequiredArgsConstructor
public class PharmacyController 
{
  private final PharmacyStockService pharmacyService;
  @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
  @PostMapping("/")
  public ResponseEntity<ApiResponse<PharmacyStockResponseDto>> addStocks(@Valid @RequestBody PharmacyStockRequestDto dto)
  {
	  PharmacyStockResponseDto stock=pharmacyService.addStock(dto);
	  ApiResponse<PharmacyStockResponseDto>response=ApiResponse.<PharmacyStockResponseDto>builder()
			  .success(true)
			  .message("Stock added successfully")
			  .statusCode(201)
			  .data(stock)
			  .build();
	  return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
  @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
  @PatchMapping("/{stockId}")
  public ResponseEntity<ApiResponse<PharmacyStockResponseDto>> updateStocks(@PathVariable Long stockId,@Valid @RequestBody PharmacyStockUpdateRequestDto dto)
  {
	  PharmacyStockResponseDto updatedStock=pharmacyService.updateStock(stockId, dto);
	  ApiResponse<PharmacyStockResponseDto>response=ApiResponse.<PharmacyStockResponseDto>builder()
			  .success(true)
			  .message("Stock updated successfully")
			  .statusCode(200)
			  .data(updatedStock)
			  .build();
	  return ResponseEntity.ok(response);
  }
  @GetMapping("/{stockId}")
  public ResponseEntity<ApiResponse<PharmacyStockResponseDto>> findStockById(@PathVariable Long stockId)
  {
	  PharmacyStockResponseDto stockById=pharmacyService.getStockById(stockId);
	  ApiResponse<PharmacyStockResponseDto>response=ApiResponse.<PharmacyStockResponseDto>builder()
			  .success(true)
			  .message("Stock founded successfully with Id: "+stockId)
			  .statusCode(200)
			  .data(stockById)
			  .build();
	  return ResponseEntity.ok(response);
  }
  @GetMapping("/lowmedicine-stock")
  public ResponseEntity<ApiResponse<List<PharmacyStockResponseDto>>> findLowStockMedicines()
  {
	  List<PharmacyStockResponseDto> stockById=pharmacyService.getLowStockMedicines();
	  ApiResponse<List<PharmacyStockResponseDto>>response=ApiResponse.<List<PharmacyStockResponseDto>>builder()
			  .success(true)
			  .message("Low stock medicine founded")
			  .statusCode(200)
			  .data(stockById)
			  .build();
	  return ResponseEntity.ok(response);
  }
  @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
  @PatchMapping("/{medicineId}/reduce-stock/{quantity}")
  public ResponseEntity<ApiResponse<String>> reduceMedicineStocks(@PathVariable Long medicineId,@PathVariable Integer quantity)
  {
	  pharmacyService.reduceMedicineStock(medicineId, quantity);;
	  ApiResponse<String>response=ApiResponse.<String>builder()
			  .success(true)
			  .message(quantity+" Medicine reduced successfully")
			  .statusCode(200)
			  .data(null)
			  .build();
	  return ResponseEntity.ok(response);
  }
  @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
  @PatchMapping("/{medicineId}")
  public ResponseEntity<ApiResponse<String>> deleteMedicineStocks(@PathVariable Long medicineId)
  {
	  pharmacyService.deleteStock(medicineId);;
	  ApiResponse<String>response=ApiResponse.<String>builder()
			  .success(true)
			  .message(" Medicine deleted successfully with Id: "+medicineId)
			  .statusCode(200)
			  .data(null)
			  .build();
	  return ResponseEntity.ok(response);
  }
}
