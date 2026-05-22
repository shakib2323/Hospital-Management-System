 package com.hms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.dto.HospitalRequestDto;
import com.hms.dto.HospitalResponseDto;
import com.hms.dto.HospitalUpdateRequestDto;
import com.hms.response.ApiResponse;
import com.hms.service.HospitalService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/hospitals")
@RequiredArgsConstructor
public class HospitalController
{
 private final HospitalService hospitalservice;
 @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
 @PostMapping("/")
 public ResponseEntity<ApiResponse<HospitalResponseDto>> registerHospital(@Valid @RequestBody HospitalRequestDto hdto)
 {
	 HospitalResponseDto hospital=hospitalservice.registerHospital(hdto);
	 ApiResponse<HospitalResponseDto>response=ApiResponse.<HospitalResponseDto>builder()
			 .success(true)
			 .message("Hospital registered successfully")
			 .statusCode(201)
			 .data(hospital)
			 .build();
	 return ResponseEntity.status(HttpStatus.CREATED).body(response);
 }
 @GetMapping("/")
 public ResponseEntity<ApiResponse<List<HospitalResponseDto>>>showAllHospitals()
 {
	 List<HospitalResponseDto> hospitals=hospitalservice.getAllHospitals();
	 ApiResponse<List<HospitalResponseDto>>response=ApiResponse.<List<HospitalResponseDto>>builder()
			 .success(true)
			 .message("All Hospitals found successfully")
			 .statusCode(200)
			 .data(hospitals)
			 .build();
	 return ResponseEntity.ok(response);
 }
 @GetMapping("/{hospitalid}")
 public ResponseEntity<ApiResponse<HospitalResponseDto>> showHospitalById(@PathVariable Long hospitalid)
 {
	 HospitalResponseDto hospital=hospitalservice.getHospitalById(hospitalid);
	 ApiResponse<HospitalResponseDto>response=ApiResponse.<HospitalResponseDto>builder()
			 .success(true)
			 .message("Hospital found successfully")
			 .statusCode(200)
			 .data(hospital)
			 .build();
	return ResponseEntity.ok(response);
	 
 }
 @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
 @PatchMapping("/{hospitalid}/update")
 public ResponseEntity<ApiResponse<HospitalResponseDto>>updateHospital(@PathVariable Long hospitalid,@Valid @RequestBody HospitalUpdateRequestDto hdto)
 {
	 HospitalResponseDto hospital=hospitalservice.updateHospital(hospitalid, hdto);
	 ApiResponse<HospitalResponseDto>response=ApiResponse.<HospitalResponseDto>builder()
			 .success(true)
			 .message("Hospitals updated successfully")
			 .statusCode(200)
			 .data(hospital)
			 .build();
	 return ResponseEntity.ok(response);
	 
 }
 @PreAuthorize("hasAnyRole('ADMIN')")
 @PatchMapping("/{hospitalid}/deactivate")
 public ResponseEntity<ApiResponse<String>> deactivateHospital( @PathVariable Long hospitalid)
 {
     hospitalservice.deactivateHospital(hospitalid);
     ApiResponse<String> response = ApiResponse.<String>builder().success(true).message("Hospital deactivated successfully").data(null).build();
     return ResponseEntity.ok(response);
 }
 @PreAuthorize("hasAnyRole('ADMIN')")
 @PatchMapping("/{hospitalid}/activate")
 public ResponseEntity<ApiResponse<String>> activateHospital( @PathVariable Long hospitalid)
 {
     hospitalservice.activateHospital(hospitalid);
     ApiResponse<String> response = ApiResponse.<String>builder().success(true).message("Hospital activated successfully").data(null).build();
     return ResponseEntity.ok(response);
 }


}
