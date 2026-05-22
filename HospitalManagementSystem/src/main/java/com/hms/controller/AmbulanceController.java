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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.dto.AmbulanceRequestDto;
import com.hms.dto.AmbulanceResponseDto;
import com.hms.response.ApiResponse;
import com.hms.service.AmbulanceService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/ambulances")
@RequiredArgsConstructor
public class AmbulanceController {
   private AmbulanceService ambulanceService;
   @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
   @PostMapping("/")
   public ResponseEntity<ApiResponse<AmbulanceResponseDto>> registerAmbulances(@Valid @RequestBody AmbulanceRequestDto dto)
   {
	   AmbulanceResponseDto ambulance=ambulanceService.registerAmbulance(dto);
	   ApiResponse<AmbulanceResponseDto> response=ApiResponse.<AmbulanceResponseDto>builder()
			   .success(true)
			   .message("Ambulance Register successfully!")
			   .statusCode(201)
			   .data(ambulance)
			   .build();
	   return ResponseEntity.status(HttpStatus.CREATED).body(response);
   }
   @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
   @PatchMapping("/{ambulanceId}/update-ambulance")
   public ResponseEntity<ApiResponse<AmbulanceResponseDto>> UpdateAmbulances(@PathVariable Long ambulanceId,@Valid @RequestBody AmbulanceRequestDto dto)
   {
	   AmbulanceResponseDto ambulanceUpdate=ambulanceService.updateAmbulance(ambulanceId, dto);
	   ApiResponse<AmbulanceResponseDto> response=ApiResponse.<AmbulanceResponseDto>builder()
			   .success(true)
			   .message("Ambulance updated successfully!")
			   .statusCode(200)
			   .data(ambulanceUpdate)
			   .build();
	   return ResponseEntity.ok(response);
   }
   @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
   @PostMapping("/{pstientId}/{ambulanceId}/assign-ambulance")
   public ResponseEntity<ApiResponse<AmbulanceResponseDto>> assignAmbulances(@PathVariable Long patientId,@PathVariable Long ambulanceId)
   {
	   AmbulanceResponseDto assignAmbulance=ambulanceService.assignAmbulance(patientId, ambulanceId);
	   ApiResponse<AmbulanceResponseDto> response=ApiResponse.<AmbulanceResponseDto>builder()
			   .success(true)
			   .message("Ambulance assign successfully!")
			   .statusCode(200)
			   .data(assignAmbulance)
			   .build();
	   return ResponseEntity.ok(response);
   }
   @GetMapping("/available")
   public ResponseEntity<ApiResponse<List<AmbulanceResponseDto>>> findAvailableAmbulance()
   {
	 List <AmbulanceResponseDto> assignAmbulance=ambulanceService.getAvailableAmbulances();
	   ApiResponse<List<AmbulanceResponseDto>> response=ApiResponse.<List<AmbulanceResponseDto>>builder()
			   .success(true)
			   .message("Available Ambulance fetched successfully!")
			   .statusCode(200)
			   .data(assignAmbulance)
			   .build();
	   return ResponseEntity.ok(response);
   }
   @GetMapping("/{ambulanceId}/track-ambulance")
   public ResponseEntity<ApiResponse<AmbulanceResponseDto>> trackAmbulances(@PathVariable Long ambulanceId)
   {
	   AmbulanceResponseDto trackAmbulance=ambulanceService.trackAmbulance(ambulanceId);
	   ApiResponse<AmbulanceResponseDto> response=ApiResponse.<AmbulanceResponseDto>builder()
			   .success(true)
			   .message("Ambulance tracked successfully!")
			   .statusCode(200)
			   .data(trackAmbulance)
			   .build();
	   return ResponseEntity.ok(response);
   }
   @PreAuthorize("hasAnyRole('ADMIN')")
   @DeleteMapping("/{ambulanceId}")
   public ResponseEntity<ApiResponse<String>> removeAmbulances(@PathVariable Long ambulanceId)
   {
	   ambulanceService.removeAmbulance(ambulanceId);
	   ApiResponse<String> response=ApiResponse.<String>builder()
			   .success(true)
			   .message("Ambulance removed successfully!")
			   .statusCode(200)
			   .data(null)
			   .build();
	   return ResponseEntity.ok(response);
   }
}
