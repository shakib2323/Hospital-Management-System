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

import com.hms.dto.BedRequestDto;
import com.hms.dto.BedResponseDto;
import com.hms.dto.BedUpdateRequestDto;
import com.hms.response.ApiResponse;
import com.hms.service.BedService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/beds")
@RequiredArgsConstructor
public class BedController
{
  private final BedService bedService;
  @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
  @PostMapping("/")
  public ResponseEntity<ApiResponse<BedResponseDto>>  addBeds(@Valid @RequestBody BedRequestDto dto)
  {
	  BedResponseDto bed=bedService.addBed(dto);
	  ApiResponse<BedResponseDto>response=ApiResponse.<BedResponseDto>builder()
			  .success(true)
			  .message("Bed added successfully")
			  .statusCode(201)
			  .data(bed)
			  .build();
	  return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
  @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
  @PatchMapping("/{bedId}/update-bed")
  public ResponseEntity<ApiResponse<BedResponseDto>>  updateBeds(@PathVariable Long bedId,@Valid @RequestBody BedUpdateRequestDto dto)
  {
	  BedResponseDto updateBed=bedService.updateBed(bedId, dto);
	  ApiResponse<BedResponseDto>response=ApiResponse.<BedResponseDto>builder()
			  .success(true)
			  .message("Bed updated successfully")
			  .statusCode(200)
			  .data(updateBed)
			  .build();
	  return ResponseEntity.ok(response);
  }
  @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
  @PostMapping("/{patientId}/assignbedtopatient/{bedId}")
  public ResponseEntity<ApiResponse<BedResponseDto>>  assignBedToPatients(@PathVariable Long patientId,@PathVariable Long bedId)
  {
	  BedResponseDto assignBed=bedService.assignBedToPatient(patientId, bedId);
	  ApiResponse<BedResponseDto>response=ApiResponse.<BedResponseDto>builder()
			  .success(true)
			  .message("Bed assign successfully")
			  .statusCode(200)
			  .data(assignBed)
			  .build();
	  return ResponseEntity.ok(response);
  }
  @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
  @PatchMapping("/{bedId}/release")
  public ResponseEntity<ApiResponse<BedResponseDto>>  releaseBeds(@PathVariable Long bedId)
  {
	  BedResponseDto relasedBed=bedService.releaseBed(bedId);
	  ApiResponse<BedResponseDto>response=ApiResponse.<BedResponseDto>builder()
			  .success(true)
			  .message("Bed relased successfully")
			  .statusCode(200)
			  .data(relasedBed)
			  .build();
	  return ResponseEntity.ok(response);
  }
  @GetMapping("/available")
  public ResponseEntity<ApiResponse<List<BedResponseDto>>>  getAvailableBeds()
  {
	  List<BedResponseDto> relasedBed=bedService.getAvailableBeds();
	  ApiResponse<List<BedResponseDto>>response=ApiResponse.<List<BedResponseDto>>builder()
			  .success(true)
			  .message("All available beds fetched successfully")
			  .statusCode(200)
			  .data(relasedBed)
			  .build();
	  return ResponseEntity.ok(response);
  }
  @GetMapping("/occpuied")
  public ResponseEntity<ApiResponse<List<BedResponseDto>>>  getOccupiedBeds()
  {
	  List<BedResponseDto> relasedBed=bedService.getOccupiedBeds();
	  ApiResponse<List<BedResponseDto>>response=ApiResponse.<List<BedResponseDto>>builder()
			  .success(true)
			  .message("All occupied beds fetched successfully")
			  .statusCode(200)
			  .data(relasedBed)
			  .build();
	  return ResponseEntity.ok(response);
  }
  @GetMapping("/{bedId}/bed")
  public ResponseEntity<ApiResponse<BedResponseDto>>  findBedById(@PathVariable Long bedId)
  {
	  BedResponseDto relasedBed=bedService.releaseBed(bedId);
	  ApiResponse<BedResponseDto>response=ApiResponse.<BedResponseDto>builder()
			  .success(true)
			  .message("Bed founded successfully")
			  .statusCode(200)
			  .data(relasedBed)
			  .build();
	  return ResponseEntity.ok(response);
  }
}
