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

import com.hms.dto.PrescriptionRequestDto;
import com.hms.dto.PrescriptionResponseDto;
import com.hms.dto.PrescriptionUpdateRequestDto;
import com.hms.response.ApiResponse;
import com.hms.service.PrescriptionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/prescriptions")
@RequiredArgsConstructor
public class PrescriptionController {
   private final PrescriptionService prescriptionService;
   @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
   @PostMapping("/")
   public ResponseEntity<ApiResponse<PrescriptionResponseDto>> createPrescriptions(@Valid @RequestBody PrescriptionRequestDto dto)
   {
	  PrescriptionResponseDto prescription=prescriptionService.createPrescription(dto);
	  ApiResponse<PrescriptionResponseDto>response=ApiResponse.<PrescriptionResponseDto>builder()
			  .success(true)
			  .message("Prescription created successfully")
			  .statusCode(201)
			  .data(prescription)
			  .build();
	  return ResponseEntity.status(HttpStatus.CREATED).body(response);
   }
   @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
   @PatchMapping("/{prescriptionId}/update")
   public ResponseEntity<ApiResponse<PrescriptionResponseDto>> updatePrescriptions(@PathVariable Long prescriptionId,@Valid @RequestBody PrescriptionUpdateRequestDto dto)
   {
	  PrescriptionResponseDto prescription=prescriptionService.updatePrescription(prescriptionId, dto);
	  ApiResponse<PrescriptionResponseDto>response=ApiResponse.<PrescriptionResponseDto>builder()
			  .success(true)
			  .message("Prescription updated successfully")
			  .statusCode(200)
			  .data(prescription)
			  .build();
	  return ResponseEntity.ok(response);
   }
   @GetMapping("/{prescriptionId}")
   public ResponseEntity<ApiResponse<PrescriptionResponseDto>> getPrescriptionById(@PathVariable Long prescriptionId)
   {
	  PrescriptionResponseDto prescription=prescriptionService.getPrescriptionById(prescriptionId);
	  ApiResponse<PrescriptionResponseDto>response=ApiResponse.<PrescriptionResponseDto>builder()
			  .success(true)
			  .message("Prescription fetched successfully with Id: "+prescriptionId)
			  .statusCode(200)
			  .data(prescription)
			  .build();
	  return ResponseEntity.ok(response);
   }
   @GetMapping("/{patientId}/patient-prescription")
   public ResponseEntity<ApiResponse<List<PrescriptionResponseDto>>> getPatientPrescription(@PathVariable Long patientId)
   {
	  List<PrescriptionResponseDto> patientPrescription=prescriptionService.getPatientPrescriptions(patientId);
	  ApiResponse<List<PrescriptionResponseDto>>response=ApiResponse.<List<PrescriptionResponseDto>>builder()
			  .success(true)
			  .message("Prescription fetched successfully with patient Id: "+patientId)
			  .statusCode(200)
			  .data(patientPrescription)
			  .build();
	  return ResponseEntity.ok(response);
   }
   @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
   @PostMapping("/{prescriptionId}/{medicineId}/add-medicine")
   public ResponseEntity<ApiResponse<String>> addMedicineToPrescriptions(@PathVariable Long prescriptionId,@PathVariable Long medicineId)
   {
	 prescriptionService.addMedicineToPrescription(prescriptionId, medicineId);
	  ApiResponse<String>response=ApiResponse.<String>builder()
			  .success(true)
			  .message("Medicine added successfully with Id: "+prescriptionId+" and medicineId: "+medicineId)
			  .statusCode(200)
			  .data(null)
			  .build();
	  return ResponseEntity.ok(response);
   }
   @PreAuthorize("hasAnyRole('ADMIN')")
   @DeleteMapping("/{prescriptionId}")
   public ResponseEntity<ApiResponse<String>> removePrescription(@PathVariable Long prescriptionId)
   {
	 prescriptionService.removePrescription(prescriptionId);
	  ApiResponse<String>response=ApiResponse.<String>builder()
			  .success(true)
			  .message("Prescription removed successfully with Id: "+prescriptionId)
			  .statusCode(200)
			  .data(null)
			  .build();
	  return ResponseEntity.ok(response);
   }
}
