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

import com.hms.dto.AppointmentResponseDto;
import com.hms.dto.BillingResponseDto;
import com.hms.dto.PatientRequestDto;
import com.hms.dto.PatientResponseDto;
import com.hms.dto.PatientUpdateRequestDto;
import com.hms.response.ApiResponse;
import com.hms.service.AppointmentService;
import com.hms.service.PatientService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/api/v1/patients")
@RequiredArgsConstructor
public class PatientController {
 private final PatientService patientService;
 private final AppointmentService appointService;
// private final BillingService billService;
 @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
 @PostMapping("/")
 public ResponseEntity<ApiResponse<PatientResponseDto>> registerPatients(@Valid @RequestBody PatientRequestDto dto)
 {
	 PatientResponseDto patient=patientService.registerPatient(dto);
	 ApiResponse<PatientResponseDto>response=ApiResponse.<PatientResponseDto>builder()
			 .success(true)
			 .message("Patient registered successfully")
			 .statusCode(201)
			 .data(patient)
			 .build();
	 return ResponseEntity.status(HttpStatus.CREATED).body(response);
 }
 @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
 @PatchMapping("/{patientId}/update")
 public ResponseEntity<ApiResponse<PatientResponseDto>> updatePatients(@PathVariable Long patientId,@Valid @RequestBody PatientUpdateRequestDto dto)
 {
	 PatientResponseDto patient=patientService.updatePatient(patientId, dto);
	 ApiResponse<PatientResponseDto>response=ApiResponse.<PatientResponseDto>builder()
			 .success(true)
			 .message("Patient updated successfully")
			 .statusCode(200)
			 .data(patient)
			 .build();
	 return ResponseEntity.ok(response);
 }
 @GetMapping("/{patientId}/patient")
 public ResponseEntity<ApiResponse<PatientResponseDto>> findPatientbyId(@PathVariable Long patientId)
 {
	 PatientResponseDto patient=patientService.getPatientById(patientId);
	 ApiResponse<PatientResponseDto>response=ApiResponse.<PatientResponseDto>builder()
			 .success(true)
			 .message("Patient founded successfully with Id: "+patientId)
			 .statusCode(200)
			 .data(patient)
			 .build();
	 return ResponseEntity.ok(response);
 }
 @GetMapping("/")
 public ResponseEntity<ApiResponse<List<PatientResponseDto>>> findAllPatient()
 {
	 List<PatientResponseDto> patient=patientService.getAllPatients();
	 ApiResponse<List<PatientResponseDto>>response=ApiResponse.<List<PatientResponseDto>>builder()
			 .success(true)
			 .message("All patient founded successfully")
			 .statusCode(200)
			 .data(patient)
			 .build();
	 return ResponseEntity.ok(response);
 }
 @GetMapping("/patients")
 public ResponseEntity<ApiResponse<List<AppointmentResponseDto>>> findPatientsAppointments()
 {
	 List<AppointmentResponseDto> patient=appointService.getAllAppointments();
	 ApiResponse<List<AppointmentResponseDto>>response=ApiResponse.<List<AppointmentResponseDto>>builder()
			 .success(true)
			 .message("All patient appointments founded successfully")
			 .statusCode(200)
			 .data(patient)
			 .build();
	 return ResponseEntity.ok(response);
 }
 @GetMapping("/{patientId}/bills")
 public ResponseEntity<ApiResponse<List<BillingResponseDto>>> findPatientsBills(@PathVariable Long patientId)
 {
	 List<BillingResponseDto> bill=patientService.getPatientBills(patientId);
	 ApiResponse<List<BillingResponseDto>>response=ApiResponse.<List<BillingResponseDto>>builder()
			 .success(true)
			 .message("Patient bills founded successfully")
			 .statusCode(200)
			 .data(bill)
			 .build();
	 return ResponseEntity.ok(response);
 }
 @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
 @PatchMapping("/{patientId}/deactivate")
 public ResponseEntity<ApiResponse<String>> deactivatePatients(@PathVariable Long patientId)
 {
	 patientService.deactivatePatient(patientId);
	 ApiResponse<String>response=ApiResponse.<String>builder()
			 .success(true)
			 .message("Patient deactivated")
			 .statusCode(200)
			 .data(null)
			 .build();
	 return ResponseEntity.ok(response);
 }
}
