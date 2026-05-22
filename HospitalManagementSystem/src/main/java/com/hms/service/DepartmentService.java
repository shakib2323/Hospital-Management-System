package com.hms.service;

import java.util.List;

import com.hms.dto.DepartmentRequestDto;
import com.hms.dto.DepartmentResponseDto;
import com.hms.dto.DepartmentUpdateRequestDto;
import com.hms.dto.DoctorResponseDto;

public interface DepartmentService 
{
	  public DepartmentResponseDto createDepartment(DepartmentRequestDto dto);
      public DepartmentResponseDto updateDepartment(Long departmentId, DepartmentUpdateRequestDto  dto);
      public DepartmentResponseDto getDepartmentById(Long departmentId);
      public List<DepartmentResponseDto> getAllDepartments();
      public List<DoctorResponseDto> getDoctorsByDepartment(Long departmentId);
      public void deleteDepartment(Long departmentId);
}
