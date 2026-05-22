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

import com.hms.dto.AppointmentRequestDto;
import com.hms.dto.AppointmentResponseDto;
import com.hms.response.ApiResponse;
import com.hms.service.AppointmentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/appointments")
@RequiredArgsConstructor
public class AppointmentController 
{
 private final AppointmentService appointmentService;
 @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
 @PostMapping("/")
 public ResponseEntity<ApiResponse<AppointmentResponseDto>> bookAppointments(@Valid @RequestBody AppointmentRequestDto dto)
 {
	 AppointmentResponseDto bookAppointment=appointmentService.bookAppointment(dto);
	 ApiResponse<AppointmentResponseDto> response=ApiResponse.<AppointmentResponseDto>builder()
			 .success(true)
			 .message("Appointment booke")
			 .statusCode(201)
			 .data(bookAppointment)
			 .build();
	 return ResponseEntity.status(HttpStatus.CREATED).body(response);
 }
 @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
 @PatchMapping("/cancel/{appointmentId}/cancel-appointment")
 public ResponseEntity<ApiResponse<AppointmentResponseDto>> cancelAppointments(@PathVariable Long appointmentId)
 {
	 AppointmentResponseDto bookAppointment=appointmentService.cancelAppointment(appointmentId);
	 ApiResponse<AppointmentResponseDto> response=ApiResponse.<AppointmentResponseDto>builder()
			 .success(true)
			 .message("Appointment canceled")
			 .statusCode(200)
			 .data(bookAppointment)
			 .build();
	 return ResponseEntity.ok(response);
 }
 @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
 @PatchMapping("/complete/{appointmentId}/complete-appointment")
 public ResponseEntity<ApiResponse<AppointmentResponseDto>> completeAppointments(@PathVariable Long appointmentId)
 {
	 AppointmentResponseDto bookAppointment=appointmentService.completeAppointment(appointmentId);
	 ApiResponse<AppointmentResponseDto> response=ApiResponse.<AppointmentResponseDto>builder()
			 .success(true)
			 .message("Appointment completed")
			 .statusCode(200)
			 .data(bookAppointment)
			 .build();
	 return ResponseEntity.ok(response);
 }
 
 @GetMapping("/{appointmentId}")
 public ResponseEntity<ApiResponse<AppointmentResponseDto>> findAppointmentsById(@PathVariable Long appointmentId)
 {
	 AppointmentResponseDto bookAppointment=appointmentService.getAppointmentById(appointmentId);
	 ApiResponse<AppointmentResponseDto> response=ApiResponse.<AppointmentResponseDto>builder()
			 .success(true)
			 .message("Appointment founded")
			 .statusCode(200)
			 .data(bookAppointment)
			 .build();
	 return ResponseEntity.ok(response);
 }
 @GetMapping("/")
 public ResponseEntity<ApiResponse<List<AppointmentResponseDto>>> findAllAppointments()
 {
	 List<AppointmentResponseDto> bookAppointment=appointmentService.getAllAppointments();
	 ApiResponse<List<AppointmentResponseDto>> response=ApiResponse.<List<AppointmentResponseDto>>builder()
			 .success(true)
			 .message("All Appointment founded")
			 .statusCode(200)
			 .data(bookAppointment)
			 .build();
	 return ResponseEntity.ok(response);
 }
 @GetMapping("/doctor/{doctorId}")
 public ResponseEntity<ApiResponse<List<AppointmentResponseDto>>> findAppointmentsByDoctor(@PathVariable Long doctorId)
 {
	 List<AppointmentResponseDto> bookAppointment=appointmentService.getAppointmentsByDoctor(doctorId);
	 ApiResponse<List<AppointmentResponseDto>> response=ApiResponse.<List<AppointmentResponseDto>>builder()
			 .success(true)
			 .message("Appointment founded by doctor with id: "+doctorId)
			 .statusCode(200)
			 .data(bookAppointment)
			 .build();
	 return ResponseEntity.ok(response);
 }
 @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
@PatchMapping("/{appointmentId}/reschedule")
public ResponseEntity<ApiResponse<AppointmentResponseDto>> rescheduleAppointment(@PathVariable Long appointmentId,@Valid @RequestBody AppointmentRequestDto dto) {
     AppointmentResponseDto appointment = appointmentService
          .rescheduleAppointment(appointmentId, dto);
     ApiResponse<AppointmentResponseDto> response = ApiResponse
          .<AppointmentResponseDto>builder()
          .success(true)
          .message("Appointment rescheduled successfully!")
          .statusCode(200)
          .data(appointment)
          .build();
  return ResponseEntity.ok(response);
}

@GetMapping("/patient/{patientId}")
public ResponseEntity<ApiResponse<List<AppointmentResponseDto>>> findAppointmentsByPatient(@PathVariable Long patientId) {
     List<AppointmentResponseDto> appointments = appointmentService
          .getAppointmentsByPatient(patientId);
     ApiResponse<List<AppointmentResponseDto>> response = ApiResponse
          .<List<AppointmentResponseDto>>builder()
          .success(true)
          .message("Appointments fetched by patient id: " + patientId)
          .statusCode(200)
          .data(appointments)
          .build();
  return ResponseEntity.ok(response);
}
}
