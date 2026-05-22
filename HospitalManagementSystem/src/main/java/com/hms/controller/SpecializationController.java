package com.hms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.dto.SpecializationRequestDto;
import com.hms.dto.SpecializationResponseDto;
import com.hms.response.ApiResponse;
import com.hms.service.SpecializationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/specializations")
@RequiredArgsConstructor
public class SpecializationController 
{
	private final SpecializationService specializationService;
 @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
 @PostMapping
 public ResponseEntity<ApiResponse<SpecializationResponseDto>> adSpecializations(@Valid @RequestBody SpecializationRequestDto dto)
 {
	 SpecializationResponseDto specialization=specializationService.addSpecialization(dto);
	 ApiResponse<SpecializationResponseDto> response=ApiResponse.<SpecializationResponseDto>builder()
			 .success(true)
			 .message("Specialization added successfully")
			 .statusCode(201)
			 .data(specialization)
			 .build();
	 return ResponseEntity.status(HttpStatus.CREATED).body(response);
 }
 @GetMapping
 public ResponseEntity<ApiResponse<List<SpecializationResponseDto>>> findAllSpecialization()
 {
	 List<SpecializationResponseDto> specializations=specializationService.getAllSpecializations();
	 ApiResponse<List<SpecializationResponseDto>> response=ApiResponse.<List<SpecializationResponseDto>>builder()
			 .success(true)
			 .message("All specializations founded successfully")
			 .statusCode(200)
			 .data(specializations)
			 .build();
			 
	 return ResponseEntity.ok(response);
 }
 @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
 @PostMapping("/{specializationId}/assign-doctor/{doctorId}")
 public ResponseEntity<ApiResponse<String>> assignDoctorToSpecializations(@PathVariable Long doctorId,@PathVariable Long specializationId)
 {
	 specializationService.assignDoctorToSpecialization(doctorId, specializationId);
	 ApiResponse<String> response = ApiResponse.<String>builder()
             .success(true)
             .message("Doctor successfully assign to specialization!")
             .statusCode(200)
             .build();
	  return ResponseEntity.ok(response);
 }
 @PreAuthorize("hasAnyRole('ADMIN')")
 @DeleteMapping("/{specializationId}")
 public ResponseEntity<ApiResponse<String>> deleteSpecializationById(@PathVariable Long specializationId)
 {
	 specializationService.removeSpecialization(specializationId);
	 ApiResponse<String> response = ApiResponse.<String>builder()
             .success(true)
             .message("Specialization deleted successfully!")
             .statusCode(200)
             .build();
     return ResponseEntity.ok(response);

 }
}
