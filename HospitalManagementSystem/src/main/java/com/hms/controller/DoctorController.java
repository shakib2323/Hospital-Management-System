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

import com.hms.dto.AppointmentResponseDto;
import com.hms.dto.DoctorRequestDto;
import com.hms.dto.DoctorResponseDto;
import com.hms.dto.DoctorUpdateRequestDto;
import com.hms.response.ApiResponse;
import com.hms.service.DoctorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/doctors")
@RequiredArgsConstructor
public class DoctorController
{
  private final DoctorService doctorService;
  @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
  @PostMapping
  public ResponseEntity<ApiResponse<DoctorResponseDto>> registerDoctors(@Valid @RequestBody DoctorRequestDto dto)
  {
	 
	  DoctorResponseDto doctor=doctorService.registerDoctor(dto);
	  ApiResponse<DoctorResponseDto> response=ApiResponse.<DoctorResponseDto>builder()
			  .success(true)
			  .message("Doctor register successfully!")
			  .statusCode(201)
			  .data(doctor)
			  .build();
	  return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
  @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
  @PatchMapping("/{doctorId}/update-doctor")
  public ResponseEntity<ApiResponse<DoctorResponseDto>> updateDoctors(@PathVariable Long doctorId,@Valid @RequestBody DoctorUpdateRequestDto dto)
  {
	  DoctorResponseDto updateDoctor=doctorService.updateDoctor(doctorId, dto);
	  ApiResponse<DoctorResponseDto> response=ApiResponse.<DoctorResponseDto>builder()
			  .success(true)
			  .message("Doctor updated successfully!")
			  .statusCode(200)
			  .data(updateDoctor)
			  .build();
	  return ResponseEntity.ok(response);
  }
  @GetMapping("/{doctorId}")
  public ResponseEntity<ApiResponse<DoctorResponseDto>> findDoctorById(@PathVariable Long doctorId)
  {
	  DoctorResponseDto doctor=doctorService.getDoctorById(doctorId);
	  ApiResponse<DoctorResponseDto> response=ApiResponse.<DoctorResponseDto>builder()
			  .success(true)
			  .message("Doctor fatched successfully!")
			  .statusCode(200)
			  .data(doctor)
			  .build();
	  return ResponseEntity.ok(response);
  }
  @GetMapping
  public ResponseEntity<ApiResponse<List<DoctorResponseDto>>> findAllDoctors()
  {
	  List<DoctorResponseDto> doctor=doctorService.getAllDoctors();
	  ApiResponse<List<DoctorResponseDto>> response=ApiResponse.<List<DoctorResponseDto>>builder()
			  .success(true)
			  .message("All Doctors fatched successfully!")
			  .statusCode(200)
			  .data(doctor)
			  .build();
	  return ResponseEntity.ok(response); 
  }
  @GetMapping("/{specialization}/specialization")
  public ResponseEntity<ApiResponse<List<DoctorResponseDto>>> findDoctorBySpecialization(String specialization)
  {
	 List <DoctorResponseDto> doctor =doctorService.searchDoctorsBySpecialization(specialization);
	 ApiResponse<List<DoctorResponseDto>> response=ApiResponse.<List<DoctorResponseDto>>builder()
			  .success(true)
			  .message("Doctor fatched successfully by specialization")
			  .statusCode(200)
			  .data(doctor)
			  .build();
	  return ResponseEntity.ok(response); 
  }
  @GetMapping("/available")
  public ResponseEntity<ApiResponse<List<DoctorResponseDto>>> findAvailableDoctors()
  {
	  List <DoctorResponseDto> doctors=doctorService.getAvailableDoctors();
	  ApiResponse<List<DoctorResponseDto>> response=ApiResponse.<List<DoctorResponseDto>>builder()
			  .success(true)
			  .message("Fatched all available doctors successfully!")
			  .statusCode(200)
			  .data(doctors)
			  .build();
	  return ResponseEntity.ok(response);
  }
  @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
  @PostMapping("/{doctorId}/assign/{specializationId}")
  public ResponseEntity<ApiResponse<String>> assignSpecializations(@PathVariable Long doctorId,@PathVariable Long specialization)
  {
	doctorService.assignSpecialization(doctorId, specialization);
	 ApiResponse<String> response=ApiResponse.<String>builder()
			  .success(true)
			  .message("Specialization assign successfully!")
			  .statusCode(200)
			  .data(null)
			  .build();
	  return ResponseEntity.ok(response);
  }
  @PreAuthorize("hasAnyRole('ADMIN')")
  @DeleteMapping("/{doctor}")
  public ResponseEntity<ApiResponse<String>> deleteDoctorById(@PathVariable Long doctorId)
  {
	  doctorService.removeDoctor(doctorId);
	  ApiResponse<String> response=ApiResponse.<String>builder()
			  .success(true)
			  .message("Doctor deleted successfully!")
			  .statusCode(200)
			  .data(null)
			  .build();
	  return ResponseEntity.ok(response);
  }
  @GetMapping("/{doctorId}/appointments")
  public ResponseEntity<ApiResponse<List<AppointmentResponseDto>>> getDoctorAppointments(@PathVariable Long doctorId) {

      List<AppointmentResponseDto> appointments = doctorService.getDoctorAppointments(doctorId);

      ApiResponse<List<AppointmentResponseDto>> response = ApiResponse.<List<AppointmentResponseDto>>builder()
              .success(true)
              .message("Doctor appointments fetched successfully!")
              .statusCode(200)
              .data(appointments)
              .build();

      return ResponseEntity.ok(response);
  }
}
