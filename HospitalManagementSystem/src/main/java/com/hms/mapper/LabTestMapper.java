package com.hms.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.hms.dto.LabTestRequestDto;
import com.hms.dto.LabTestResponseDto;
import com.hms.entity.Doctor;
import com.hms.entity.LabTest;
import com.hms.entity.Patient;

@Component
public class LabTestMapper 
{
    public LabTest toEntity(LabTestRequestDto dto)
    {
        LabTest labTest = new LabTest();
        Patient patient=new Patient();
        Doctor doctor=new Doctor();
        labTest.setTestName(dto.getTestName());
        labTest.setTestCode(dto.getTestCode());
        labTest.setDescription(dto.getDescription());
        labTest.setTestCost(dto.getTestCost());
        labTest.setNormalRange(dto.getNormalRange());
        labTest.setStatus(dto.getStatus());
        labTest.setSampleType(dto.getSampleType());
        labTest.setPatient(patient);
        labTest.setDoctor(doctor);
        labTest.setCreatedBy(dto.getCreatedBy());
        labTest.setUpdatedBy(dto.getUpdatedBy());
        return labTest;
    }

    public LabTest updateEntity(LabTest labTest, LabTestRequestDto dto)
    {
    	 Patient patient=new Patient();
         Doctor doctor=new Doctor();
        labTest.setTestName(dto.getTestName());
        labTest.setTestCode(dto.getTestCode());
        labTest.setDescription(dto.getDescription());
        labTest.setTestCost(dto.getTestCost());
        labTest.setNormalRange(dto.getNormalRange());
        labTest.setStatus(dto.getStatus());
        labTest.setSampleType(dto.getSampleType());
        labTest.setPatient(patient);
        labTest.setDoctor(doctor);
        labTest.setUpdatedBy(dto.getUpdatedBy());
		return labTest;
    }
    public LabTestResponseDto toDto(LabTest labTest)
    {
        LabTestResponseDto dto = new LabTestResponseDto();
        dto.setId(labTest.getLabTestId());
        dto.setTestName(labTest.getTestName());
        dto.setTestCode(labTest.getTestCode());
        dto.setDescription(labTest.getDescription());
        dto.setTestCost(labTest.getTestCost());
        dto.setNormalRange(labTest.getNormalRange());
        dto.setStatus(labTest.getStatus());
        dto.setSampleType(labTest.getSampleType());
        dto.setPatientId(labTest.getPatient().getPatientId());
        dto.setPatientName(labTest.getPatient().getFirstName() + " " + labTest.getPatient().getLastName());
        dto.setDoctorId(labTest.getDoctor().getDoctorId());
        dto.setDoctorName(labTest.getDoctor().getFirstName() + " " + labTest.getDoctor().getLastName());
        dto.setCreatedBy(labTest.getCreatedBy());
        dto.setUpdatedBy(labTest.getUpdatedBy());
        dto.setCreatedAt(labTest.getCreatedAt());
        dto.setUpdatedAt(labTest.getUpdatedAt());
        return dto;
    }
    public List<LabTestResponseDto> toDtoList(List<LabTest> labTest)
    {
    	return labTest.stream().map(this::toDto).collect(Collectors.toList());
    }
}