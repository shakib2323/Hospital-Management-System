package com.hms.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.hms.dto.DoctorRequestDto;
import com.hms.dto.DoctorResponseDto;
import com.hms.entity.Department;
import com.hms.entity.Doctor;
import com.hms.entity.User;
import com.hms.exception.ResourceNotFoundException;
import com.hms.repository.DepartmentRepository;
import com.hms.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DoctorMapper 
{
    private final DepartmentRepository departmentRepo;
    private final UserRepository userRepo;
    public Doctor toEntity(DoctorRequestDto dto)
    {
        Doctor doctor = new Doctor();
        doctor.setDoctorCode(dto.getDoctorCode());
        doctor.setFirstName(dto.getFirstName());
        doctor.setLastName(dto.getLastName());
        doctor.setEmail(dto.getEmail());
        doctor.setPhoneNumber(dto.getPhoneNumber());
        doctor.setGender(dto.getGender());
        doctor.setDateOfBirth(dto.getDateOfBirth());
        doctor.setQualification(dto.getQualification());
        doctor.setExperienceYears(dto.getExperienceYears());
        doctor.setConsultationFee(dto.getConsultationFee());
        doctor.setLicenseNumber(dto.getLicenseNumber());
        doctor.setSpecialNote(dto.getSpecialNote());
        doctor.setAvailabilityStartTime(dto.getAvailabilityStartTime());
        doctor.setAvailabilityEndTime(dto.getAvailabilityEndTime());
        doctor.setStatus(dto.getStatus());
        // Fetch and set Department
        Department dept = departmentRepo.findById(dto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + dto.getDepartmentId()));
        doctor.setDepartment(dept);
        // Fetch and set User
        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + dto.getUserId()));
        doctor.setUser(user);
        doctor.setCreatedBy(dto.getCreatedBy());
        doctor.setUpdatedBy(dto.getUpdatedBy());
        return doctor;
    }

    public DoctorResponseDto toDto(Doctor doctor) {
        DoctorResponseDto dto = new DoctorResponseDto();
        dto.setDoctorId(doctor.getDoctorId());      
        dto.setDoctorCode(doctor.getDoctorCode());     
        dto.setFirstName(doctor.getFirstName());
        dto.setLastName(doctor.getLastName());
        dto.setEmail(doctor.getEmail());
        dto.setPhoneNumber(doctor.getPhoneNumber());
        dto.setGender(doctor.getGender());
        dto.setDateOfBirth(doctor.getDateOfBirth());
        dto.setQualification(doctor.getQualification());
        dto.setExperienceYears(doctor.getExperienceYears());
        dto.setConsultationFee(doctor.getConsultationFee());
        dto.setLicenseNumber(doctor.getLicenseNumber());
        dto.setSpecialNote(doctor.getSpecialNote());
        dto.setAvailabilityStartTime(doctor.getAvailabilityStartTime());
        dto.setAvailabilityEndTime(doctor.getAvailabilityEndTime());
        dto.setStatus(doctor.getStatus());
        // Department info
        dto.setDepartmentId(doctor.getDepartment().getDepartmentId());
        dto.setDepartmentName(doctor.getDepartment().getDepartmentName());
        // User info
        dto.setUserId(doctor.getUser().getUserId());
        dto.setCreatedBy(doctor.getCreatedBy());
        dto.setUpdatedBy(doctor.getUpdatedBy());
        dto.setCreatedAt(doctor.getCreatedAt());      
        dto.setUpdatedAt(doctor.getUpdatedAt());
        return dto;
    }

    public List<DoctorResponseDto> toDtoList(List<Doctor> doctors) 
    {
        return doctors.stream().map(this::toDto).collect(Collectors.toList());
    }
}