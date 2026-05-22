package com.hms.controller;

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

import com.hms.dto.LabTestRequestDto;
import com.hms.dto.LabTestResponseDto;
import com.hms.response.ApiResponse;
import com.hms.service.LabTestService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/labtests")
@RequiredArgsConstructor
public class LabTestController
{
  private final LabTestService labtestService;
  @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
  @PostMapping("/")
  public ResponseEntity<ApiResponse<LabTestResponseDto>>createLabTests(@Valid @RequestBody LabTestRequestDto dto)
  {
	  LabTestResponseDto labtest=labtestService.createLabTest(dto);
	  ApiResponse<LabTestResponseDto>response=ApiResponse.<LabTestResponseDto>builder()
			  .success(true)
			  .message("LabTest created successfully")
			  .statusCode(201)
			  .data(labtest)
			  .build();
	  return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
  @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
  @PatchMapping("/{labtestId}/update-lab-test")
  public ResponseEntity<ApiResponse<LabTestResponseDto>>updateLabTests(@PathVariable Long labTestId,@Valid @RequestBody LabTestRequestDto dto)
  {
	  LabTestResponseDto labtest=labtestService.createLabTest(dto);
	  ApiResponse<LabTestResponseDto>response=ApiResponse.<LabTestResponseDto>builder()
			  .success(true)
			  .message("LabTest updated successfully")
			  .statusCode(200)
			  .data(labtest)
			  .build();
	  return ResponseEntity.ok(response);
  }
  @GetMapping("/{labtestId}")
  public ResponseEntity<ApiResponse<LabTestResponseDto>>getLabTestById(@PathVariable Long labTestId)
  {
	  LabTestResponseDto labtest=labtestService.getLabTestById(labTestId);
	  ApiResponse<LabTestResponseDto>response=ApiResponse.<LabTestResponseDto>builder()
			  .success(true)
			  .message("LabTest founded successfully")
			  .statusCode(200)
			  .data(labtest)
			  .build();
	  return ResponseEntity.ok(response);
  }
  @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
  @PutMapping("/{labtestId}/{technicianName}")
  public ResponseEntity<ApiResponse<String>>assignLabTechnician(@PathVariable Long labTestId,@PathVariable String technicianName)
  {
	 labtestService.assignLabTechnician(labTestId, technicianName);;
	  ApiResponse<String>response=ApiResponse.<String>builder()
			  .success(true)
			  .message("Technician assign successfully")
			  .statusCode(200)
			  .data(null)
			  .build();
	  return ResponseEntity.ok(response);
  }
  @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
  @PatchMapping("/{labtestId}/cancel")
  public ResponseEntity<ApiResponse<String>>cancelTest(@PathVariable Long labTestId)
  {
	  labtestService.cancelLabTest(labTestId);;
	  ApiResponse<String>response=ApiResponse.<String>builder()
			  .success(true)
			  .message("LabTest canceled successfully")
			  .statusCode(200)
			  .data(null)
			  .build();
	  return ResponseEntity.ok(response);
  }
}
