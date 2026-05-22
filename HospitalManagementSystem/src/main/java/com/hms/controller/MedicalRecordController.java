package com.hms.controller;

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

import com.hms.dto.MedicalRecordRequestDto;
import com.hms.dto.MedicalRecordResponseDto;
import com.hms.response.ApiResponse;
import com.hms.service.MedicalRecordService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/medicalrecords")
@RequiredArgsConstructor
public class MedicalRecordController {
  private final MedicalRecordService medicalRecordService;
  @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
  @PostMapping("/{patientId}")
  public ResponseEntity<ApiResponse<MedicalRecordResponseDto>>createMedicalRecords(@PathVariable Long patientId,@Valid @RequestBody MedicalRecordRequestDto dto)
  {
	  MedicalRecordResponseDto medicalRecord=medicalRecordService.createMedicalRecord(patientId, dto);
	  ApiResponse<MedicalRecordResponseDto>response=ApiResponse.<MedicalRecordResponseDto>builder()
			  .success(true)
			  .message("MedicalRecord created successfully")
			  .statusCode(201)
			  .data(medicalRecord)
			  .build();
	  return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
  @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
  @PatchMapping("/{recordId}/update")
  public ResponseEntity<ApiResponse<MedicalRecordResponseDto>> updateMedicalRecords(@PathVariable Long recordId,@Valid @RequestBody MedicalRecordRequestDto dto)
  {
	  MedicalRecordResponseDto updateMedicalRecord=medicalRecordService.updateMedicalRecord(recordId, dto);
	  ApiResponse<MedicalRecordResponseDto>response=ApiResponse.<MedicalRecordResponseDto>builder()
			  .success(true)
			  .message("MedicalRecord updated successfully")
			  .statusCode(200)
			  .data(updateMedicalRecord)
			  .build();
	  return ResponseEntity.ok(response);
  }
  @GetMapping("/{patientId}/patient-record")
  public ResponseEntity<ApiResponse<MedicalRecordResponseDto>> findMedicalRecordByPatient(@PathVariable Long patientId)
  {
	  MedicalRecordResponseDto recordByPatient=medicalRecordService.getMedicalRecordByPatient(patientId);
	  ApiResponse<MedicalRecordResponseDto>response=ApiResponse.<MedicalRecordResponseDto>builder()
			  .success(true)
			  .message("MedicalRecord founded successfully with id: "+patientId)
			  .statusCode(200)
			  .data(recordByPatient)
			  .build();
	  return ResponseEntity.ok(response);
  }
  @PreAuthorize("hasAnyRole('ADMIN')")
  @DeleteMapping("/{recordId}")
  public ResponseEntity<ApiResponse<String>> deleteMedicalRecords(@PathVariable Long recordId)
  {
	  medicalRecordService.getMedicalRecordByPatient(recordId);
	  ApiResponse<String>response=ApiResponse.<String>builder()
			  .success(true)
			  .message("MedicalRecord deleted successfully with id: "+recordId)
			  .statusCode(200)
			  .data(null)
			  .build();
	  return ResponseEntity.ok(response); 
  }
}
