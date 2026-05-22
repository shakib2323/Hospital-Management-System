package com.hms.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.hms.dto.PatientRequestDto;
import com.hms.dto.PatientResponseDto;
import com.hms.entity.Patient;
import com.hms.entity.User;
import com.hms.exception.ResourceNotFoundException;
import com.hms.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PatientMapper
{
    private final UserRepository userRepo;
    public Patient toEntity(PatientRequestDto dto)
    {
        Patient patient = new Patient();
        patient.setPatientCode(dto.getPatientCode());
        patient.setFirstName(dto.getFirstName());
        patient.setLastName(dto.getLastName());
        patient.setEmail(dto.getEmail());         
        patient.setPhoneNumber(dto.getPhoneNumber());
        patient.setGender(dto.getGender());
        patient.setDateOfBirth(dto.getDateOfBirth());
        patient.setBloodGroup(dto.getBloodGroup());
        patient.setMaritalStatus(dto.getMaritalStatus());
        patient.setOccupation(dto.getOccupation());
        patient.setHeight(dto.getHeight());
        patient.setWeight(dto.getWeight());
        patient.setAllergies(dto.getAllergies());
        patient.setEmergencyContactName(dto.getEmergencyContactName());
        patient.setEmergencyContactNumber(dto.getEmergencyContactNumber());
        patient.setInsuranceProvider(dto.getInsuranceProvider());
        patient.setInsurancePolicyNumber(dto.getInsurancePolicyNumber());
        patient.setAddress(dto.getAddress());
        patient.setCity(dto.getCity());
        patient.setState(dto.getState());
        patient.setPincode(dto.getPincode());
        patient.setCreatedBy(dto.getCreatedBy());
        patient.setUpdatedBy(dto.getUpdatedBy());
        if (dto.getUserId() != null) {
            User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + dto.getUserId()));
            patient.setUser(user);
        }
        return patient;
    }
    public PatientResponseDto toDto(Patient patient) 
    {
        PatientResponseDto dto = new PatientResponseDto();
        dto.setPatientId(patient.getPatientId());
        dto.setPatientCode(patient.getPatientCode());
        dto.setFirstName(patient.getFirstName());
        dto.setLastName(patient.getLastName());
        dto.setEmail(patient.getEmail());
        dto.setPhoneNumber(patient.getPhoneNumber());
        dto.setGender(patient.getGender());
        dto.setDateOfBirth(patient.getDateOfBirth());
        dto.setBloodGroup(patient.getBloodGroup());
        dto.setMaritalStatus(patient.getMaritalStatus());
        dto.setOccupation(patient.getOccupation());
        dto.setHeight(patient.getHeight());
        dto.setWeight(patient.getWeight());
        dto.setAllergies(patient.getAllergies());
        dto.setEmergencyContactName(patient.getEmergencyContactName());
        dto.setEmergencyContactNumber(patient.getEmergencyContactNumber());
        dto.setInsuranceProvider(patient.getInsuranceProvider());
        dto.setInsurancePolicyNumber(patient.getInsurancePolicyNumber());
        dto.setAddress(patient.getAddress());
        dto.setCity(patient.getCity());
        dto.setState(patient.getState());
        dto.setPincode(patient.getPincode());
        dto.setUserId(patient.getUser().getUserId());
        dto.setCreatedBy(patient.getCreatedBy());
        dto.setUpdatedBy(patient.getUpdatedBy());
        dto.setCreatedAt(patient.getCreatedAt());
        dto.setUpdatedAt(patient.getUpdatedAt());
        return dto;
    }

    public List<PatientResponseDto> toDtoList(List<Patient> patients)
    {
        return patients.stream().map(this::toDto).collect(Collectors.toList());
    }
}
