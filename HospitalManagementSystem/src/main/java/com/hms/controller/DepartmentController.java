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

import com.hms.dto.DepartmentRequestDto;
import com.hms.dto.DepartmentResponseDto;
import com.hms.dto.DepartmentUpdateRequestDto;
import com.hms.dto.DoctorResponseDto;
import com.hms.response.ApiResponse;
import com.hms.service.DepartmentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
public class DepartmentController 
{
	private final DepartmentService departmentService;
 @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','LAB_TECHNICIAN','NURSE','ACCOUNTENT','HR_MANAGER')")
 @PostMapping("/register")
 public ResponseEntity<ApiResponse<DepartmentResponseDto>> registerDepartment(@Valid @RequestBody DepartmentRequestDto dto)
 {
	 DepartmentResponseDto department=departmentService.createDepartment(dto);
	 ApiResponse<DepartmentResponseDto> response = ApiResponse.<DepartmentResponseDto>builder()
	            .success(true)
	            .message("Department inserted successfully")
	            .statusCode(201)
	            .data(department)
	            .build();
	return ResponseEntity.status(HttpStatus.CREATED).body(response); 
 }
 @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','LAB_TECHNICIAN','NURSE','ACCOUNTENT','HR_MANAGER')")
 @PatchMapping("/{departmentid}/update-department")
 public ResponseEntity<ApiResponse<DepartmentResponseDto>> updateDepartments(@PathVariable Long departmentid, @Valid @RequestBody DepartmentUpdateRequestDto dto)
 {
	 DepartmentResponseDto department=departmentService.updateDepartment(departmentid, dto);
	 ApiResponse<DepartmentResponseDto> response=ApiResponse.<DepartmentResponseDto>builder()
			 .success(true)
			 .message("Department updated successfully")
			 .statusCode(200)
			 .data(department)
			 .build();
	 return ResponseEntity.ok(response);
 }
 @GetMapping("/")
 public ResponseEntity<ApiResponse<List<DepartmentResponseDto>>> findAllDepartments() {
     List<DepartmentResponseDto> departments = departmentService.getAllDepartments();

     ApiResponse<List<DepartmentResponseDto>> response = ApiResponse.<List<DepartmentResponseDto>>builder()
             .success(true)
             .message("All departments fetched successfully!")
             .statusCode(200)
             .data(departments)
             .build();

     return ResponseEntity.ok(response);
 }
 @GetMapping("/{departmentId}/department")
 public ResponseEntity<ApiResponse<DepartmentResponseDto>> findDepartmentById(@PathVariable Long departmentId)
 {
	 DepartmentResponseDto department=departmentService.getDepartmentById(departmentId);
	 ApiResponse<DepartmentResponseDto> response=ApiResponse.<DepartmentResponseDto>builder()
			 .success(true)
			 .message("Department fatched successfully")
			 .statusCode(200)
			 .data(department)
			 .build();
	 return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
 }
 @GetMapping("/{departmentid}/doctors")
 public ResponseEntity<ApiResponse<List<DoctorResponseDto>>> findDoctorByDepartment(@PathVariable Long departmentid) {

     List<DoctorResponseDto> doctors = departmentService.getDoctorsByDepartment(departmentid);

     ApiResponse<List<DoctorResponseDto>> response = ApiResponse.<List<DoctorResponseDto>>builder()
             .success(true)
             .message("Doctors fetched successfully!")
             .statusCode(200)
             .data(doctors)
             .build();

     return ResponseEntity.ok(response);
 }
 @PreAuthorize("'hasAnyRole('ADMIN')'")
 @DeleteMapping("/{departmentid}/delete")
 public ResponseEntity<ApiResponse<String>> deleteDepartmentById(@PathVariable Long departmentid) {
     departmentService.deleteDepartment(departmentid);
     ApiResponse<String> response = ApiResponse.<String>builder()
             .success(true)
             .message("Department deleted successfully!")
             .statusCode(200)
             .build();
     return ResponseEntity.ok(response);
 }
}
