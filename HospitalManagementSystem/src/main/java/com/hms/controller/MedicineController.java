package com.hms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.dto.MedicineRequestDto;
import com.hms.dto.MedicineResponseDto;
import com.hms.dto.MedicineUpdateRequestDto;
import com.hms.response.ApiResponse;
import com.hms.service.MedicineService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/medicines")
@RequiredArgsConstructor
public class MedicineController 
{
  private final MedicineService medicineService;
  @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
  @PostMapping("/")
  public ResponseEntity<ApiResponse<MedicineResponseDto>>addMedicines(@Valid @RequestBody MedicineRequestDto dto)
  {
	  MedicineResponseDto medicine=medicineService.addMedicine(dto);
	  ApiResponse<MedicineResponseDto>response=ApiResponse.<MedicineResponseDto>builder()
			  .success(true)
			  .message("Medicine added successfully")
			  .statusCode(201)
			  .data(medicine)
			  .build();
	  return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
  @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
  @PatchMapping("/{medicineId}/update")
  public ResponseEntity<ApiResponse<MedicineResponseDto>>updateMedicines(@PathVariable Long medicineId,@Valid @RequestBody MedicineUpdateRequestDto dto)
  {
	  MedicineResponseDto medicineUpdate=medicineService.updateMedicine(medicineId,dto);
	  ApiResponse<MedicineResponseDto>response=ApiResponse.<MedicineResponseDto>builder()
			  .success(true)
			  .message("Medicine updated successfully")
			  .statusCode(200)
			  .data(medicineUpdate)
			  .build();
	  return ResponseEntity.ok(response);
  }
  
  @GetMapping("/{medicineId}")
  public ResponseEntity<ApiResponse<MedicineResponseDto>>findMedicineById(@PathVariable Long medicineId)
  {
	  MedicineResponseDto medicineById=medicineService.getMedicineById(medicineId);
	  ApiResponse<MedicineResponseDto>response=ApiResponse.<MedicineResponseDto>builder()
			  .success(true)
			  .message("Medicine founded successfully with Id: "+medicineId)
			  .statusCode(200)
			  .data(medicineById)
			  .build();
	  return ResponseEntity.ok(response);
  }
  @GetMapping("/")
  public ResponseEntity<ApiResponse<List<MedicineResponseDto>>>findAllMedicine() {
	  List<MedicineResponseDto> medicines=medicineService.getAllMedicines();
	  ApiResponse<List<MedicineResponseDto>>response=ApiResponse.<List<MedicineResponseDto>>builder()
			  .success(true)
			  .message("All Medicine founded successfully")
			  .statusCode(200)
			  .data(medicines)
			  .build();
	  return ResponseEntity.ok(response);
  }
  @GetMapping("/name/{medicineName}")
  public ResponseEntity<ApiResponse<List<MedicineResponseDto>>>findMedicineByName(@PathVariable String medicineName) {
	  List<MedicineResponseDto> medicines=medicineService.searchMedicineByName(medicineName);
	  ApiResponse<List<MedicineResponseDto>>response=ApiResponse.<List<MedicineResponseDto>>builder()
			  .success(true)
			  .message("Medicine founded successfully with name: "+medicineName)
			  .statusCode(200)
			  .data(medicines)
			  .build();
	  return ResponseEntity.ok(response);
  }
  @PreAuthorize("hasAnyRole('ADMIN')")
  @DeleteMapping("/delete/{medicineId}")
  public ResponseEntity<ApiResponse<String>> deleteMedicine(@PathVariable Long medicineId)
  {
	  medicineService.deleteMedicine(medicineId);;
	  ApiResponse<String>response=ApiResponse.<String>builder()
			  .success(true)
			  .message("Medicine deleted successfully with Id: "+medicineId)
			  .statusCode(200)
			  .data(null)
			  .build();
	  return ResponseEntity.ok(response);
  }
}
