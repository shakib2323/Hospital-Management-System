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

import com.hms.dto.AdmissionRequestDto;
import com.hms.dto.AdmissionResponseDto;
import com.hms.response.ApiResponse;
import com.hms.service.AdmissionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/admissions")
@RequiredArgsConstructor
public class AdmissionController 
{
  private final AdmissionService admissionService;
  @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
  @PostMapping("/")
  public ResponseEntity<ApiResponse<AdmissionResponseDto>> admitPatients(@Valid @RequestBody AdmissionRequestDto dto)
  {
	 AdmissionResponseDto admission= admissionService.admitPatient(dto);
	 ApiResponse<AdmissionResponseDto> response=ApiResponse.<AdmissionResponseDto>builder()
			 .success(true)
			 .message("Patient admited successfully!")
			 .statusCode(201)
			 .data(admission)
			 .build();
	 return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
  @PostMapping("/{admissionId}/discharge")
  public ResponseEntity<ApiResponse<AdmissionResponseDto>> dischargePatients(@PathVariable Long admissionId)
  {
	  AdmissionResponseDto patient=admissionService.dischargePatient(admissionId);
	  ApiResponse<AdmissionResponseDto> response=ApiResponse.<AdmissionResponseDto>builder()
				 .success(true)
				 .message("Patient discharged successfully!")
				 .statusCode(200)
				 .data(patient)
				 .build();
	  return ResponseEntity.ok(response);
  }
  @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
  @PostMapping("/{admissionId}/transfer/{newBedId}")
  public ResponseEntity<ApiResponse<AdmissionResponseDto>> transferPatient(@PathVariable Long admissionId,@PathVariable Long newBedId)
  {
	  AdmissionResponseDto patientTransfer=admissionService.transferPatient(admissionId, newBedId);
	  ApiResponse<AdmissionResponseDto> response=ApiResponse.<AdmissionResponseDto>builder()
				 .success(true)
				 .message("Patient transfered successfully!")
				 .statusCode(200)
				 .data(patientTransfer)
				 .build();
	  return ResponseEntity.ok(response);
  }
  @GetMapping("/{admissionId}")
  public ResponseEntity<ApiResponse<AdmissionResponseDto>> findAdmissionById(@PathVariable Long admissionId)
  {
	  AdmissionResponseDto admission=admissionService.getAdmissionById(admissionId);
	  ApiResponse<AdmissionResponseDto> response=ApiResponse.<AdmissionResponseDto>builder()
				 .success(true)
				 .message("Admission fetched successfully!")
				 .statusCode(200)
				 .data(admission)
				 .build();
	  return ResponseEntity.ok(response);
  }
  @GetMapping("/active-admission")
  public ResponseEntity<ApiResponse<List<AdmissionResponseDto>>> findActiveAdmission()
  {
	  List<AdmissionResponseDto> admission=admissionService.getActiveAdmissions();
	  ApiResponse<List<AdmissionResponseDto>> response=ApiResponse.<List<AdmissionResponseDto>>builder()
				 .success(true)
				 .message("Fetched all active admission successfully!")
				 .statusCode(200)
				 .data(admission)
				 .build();
	  return ResponseEntity.ok(response);
  }
  @GetMapping("/{patientId}/patient-admission")
  public ResponseEntity<ApiResponse<List<AdmissionResponseDto>>> findPatientAdmissionById(@PathVariable Long patientId)
  {
	  List<AdmissionResponseDto> admission=admissionService.getPatientAdmissions(patientId);
	  ApiResponse<List<AdmissionResponseDto>> response=ApiResponse.<List<AdmissionResponseDto>>builder()
				 .success(true)
				 .message("Patients fetched successfully!")
				 .statusCode(200)
				 .data(admission)
				 .build();
	  return ResponseEntity.ok(response);
  }
  
}
