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

import com.hms.dto.LabReportRequestDto;
import com.hms.dto.LabReportResponseDto;
import com.hms.response.ApiResponse;
import com.hms.service.LabReportService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/labreport")
@RequiredArgsConstructor
public class LabReportController {
  private final LabReportService labreportService;
  @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
  @PostMapping("/")
  public ResponseEntity<ApiResponse<LabReportResponseDto>>generateLabReports(@Valid @RequestBody LabReportRequestDto dto)
  {
	  LabReportResponseDto labreport=labreportService.generateLabReport(dto);
	  ApiResponse<LabReportResponseDto>response=ApiResponse.<LabReportResponseDto>builder()
			  .success(true)
			  .message("LabTest report generated successfully!")
			  .statusCode(201)
			  .data(labreport)
			  .build();
	  return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
  @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
  @PatchMapping("/{reportId}/update")
  public ResponseEntity<ApiResponse<LabReportResponseDto>>updateLabReports(@PathVariable Long reportId,@Valid @RequestBody LabReportRequestDto dto)
  {
	  LabReportResponseDto labreport=labreportService.updateLabReport(reportId, dto);
	  ApiResponse<LabReportResponseDto>response=ApiResponse.<LabReportResponseDto>builder()
			  .success(true)
			  .message("LabTest report updated successfully!")
			  .statusCode(200)
			  .data(labreport)
			  .build();
	  return ResponseEntity.ok(response);
  }
  @GetMapping("/{labtestId}/report")
  public ResponseEntity<ApiResponse<LabReportResponseDto>>findReportById(@PathVariable Long reportId)
  {
	  LabReportResponseDto labreport=labreportService.getReportById(reportId);
	  ApiResponse<LabReportResponseDto>response=ApiResponse.<LabReportResponseDto>builder()
			  .success(true)
			  .message("LabTest report founded successfully with report id: "+reportId)
			  .statusCode(200)
			  .data(labreport)
			  .build();
	  return ResponseEntity.ok(response);
  }
  @GetMapping("/{patientId}/patient-report")
  public ResponseEntity<ApiResponse<List<LabReportResponseDto>>>findPatientReport(@PathVariable Long patientId)
  {
	  List<LabReportResponseDto> labreport=labreportService.getPatientReports(patientId);
	  ApiResponse<List<LabReportResponseDto>>response=ApiResponse.<List<LabReportResponseDto>>builder()
			  .success(true)
			  .message("Patient report founded successfully with patient id: "+patientId)
			  .statusCode(200)
			  .data(labreport)
			  .build();
	  return ResponseEntity.ok(response);
  }
  @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
  @PatchMapping("/{reportId}/approve")
  public ResponseEntity<ApiResponse<String>>approveReport(@PathVariable Long reportId)
  {
	  labreportService.approveReport(reportId);;
	  ApiResponse<String>response=ApiResponse.<String>builder()
			  .success(true)
			  .message("Report approved successfully with report id: "+reportId)
			  .statusCode(200)
			  .data(null)
			  .build();
	  return ResponseEntity.ok(response);
  }
}
