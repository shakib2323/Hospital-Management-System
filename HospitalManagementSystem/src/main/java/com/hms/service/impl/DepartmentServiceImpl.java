package com.hms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hms.dto.DepartmentRequestDto;
import com.hms.dto.DepartmentResponseDto;
import com.hms.dto.DepartmentUpdateRequestDto;
import com.hms.dto.DoctorResponseDto;
import com.hms.entity.Department;
import com.hms.entity.Doctor;
import com.hms.exception.ResourceNotFoundException;
import com.hms.mapper.DepartmentMapper;
import com.hms.mapper.DoctorMapper;
import com.hms.repository.DepartmentRepository;
import com.hms.repository.DoctorRepository;
import com.hms.service.DepartmentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepo;
    private final DepartmentMapper departmentMapper;
    private final DoctorRepository doctorRepo;
    private final DoctorMapper doctorMapper; 
    @Override
    public DepartmentResponseDto createDepartment(DepartmentRequestDto dto) {
        Department department = departmentMapper.toEntity(dto);
        departmentRepo.save(department);
        return departmentMapper.toDto(department);
    }

    @Override
    public DepartmentResponseDto updateDepartment(Long departmentId, DepartmentUpdateRequestDto dto) {
        Department department = departmentRepo.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department Not found!"));

      
        if (dto.getBranchId() != null) 
        {
            throw new IllegalArgumentException("BranchId is not an updatable field!");
        }
        if (dto.getDepartmentCode() != null)
        {
            throw new IllegalArgumentException("DepartmentCode is not an updatable field!");
        }
        department.setDepartmentName(dto.getDepartmentName());
        department.setDescription(dto.getDescription());
        department.setFloorNumber(dto.getFloorNumber());
        department.setContactExtension(dto.getContactExtension());

        departmentRepo.save(department); 
        return departmentMapper.toDto(department);
    }

    @Override
    public DepartmentResponseDto getDepartmentById(Long departmentId) {
        Department department = departmentRepo.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department Not found!"));
        return departmentMapper.toDto(department);
    }

    @Override
    public List<DepartmentResponseDto> getAllDepartments() {
        return departmentMapper.toDtoList(departmentRepo.findAll());
    }

    @Override
    public List<DoctorResponseDto> getDoctorsByDepartment(Long departmentId) {
    	 Department department = departmentRepo.findById(departmentId)
                 .orElseThrow(() -> new ResourceNotFoundException("Department Not found!"));
    	 List<Doctor> doctors = doctorRepo.findByDepartment(department); 
    	    return doctorMapper.toDtoList(doctors);  
    }

    @Override
    public void deleteDepartment(Long departmentId) {
        Department department = departmentRepo.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department Not found!"));
        departmentRepo.delete(department);
    }
}