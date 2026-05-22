package com.hms.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.hms.dto.LabReportRequestDto;
import com.hms.dto.LabReportResponseDto;
import com.hms.entity.LabReport;
import com.hms.entity.LabTest;

@Component
public class LabReportMapper
{
    public LabReport toEntity(LabReportRequestDto dto) 
    {
        LabReport labReport = new LabReport();
        LabTest labTest=new LabTest();
        labReport.setReportDate(dto.getReportDate());
        labReport.setResultSummary(dto.getResultSummary());
        labReport.setObservations(dto.getObservations());
        labReport.setRecommendation(dto.getRecommendation());
        labReport.setTechnicianName(dto.getTechnicianName());
        labReport.setApprovedBy(dto.getApprovedBy());
        labReport.setLabTest(labTest);
        labReport.setCreatedBy(dto.getCreatedBy());
        labReport.setUpdatedBy(dto.getUpdatedBy());
        return labReport;
    }

    public LabReport updateEntity(LabReport labReport, LabReportRequestDto dto) 
    {
    	 LabTest labTest=new LabTest();
        labReport.setReportDate(dto.getReportDate());
        labReport.setResultSummary(dto.getResultSummary());
        labReport.setObservations(dto.getObservations());
        labReport.setRecommendation(dto.getRecommendation());
        labReport.setTechnicianName(dto.getTechnicianName());
        labReport.setApprovedBy(dto.getApprovedBy());
        labReport.setLabTest(labTest);
        labReport.setUpdatedBy(dto.getUpdatedBy());
        return labReport;
    }
    public LabReportResponseDto toDto(LabReport labReport)
    {
        LabReportResponseDto dto = new LabReportResponseDto();
        dto.setReportId(labReport.getReportId());
        dto.setReportDate(labReport.getReportDate());
        dto.setResultSummary(labReport.getResultSummary());
        dto.setObservations(labReport.getObservations());
        dto.setRecommendation(labReport.getRecommendation());
        dto.setTechnicianName(labReport.getTechnicianName());
        dto.setApprovedBy(labReport.getApprovedBy());
        LabTest labTest = labReport.getLabTest();
        dto.setLabTestId(labTest.getLabTestId());
        dto.setTestName(labTest.getTestName());
        dto.setTestCode(labTest.getTestCode());
        dto.setSampleType(labTest.getSampleType());
        dto.setLabTestStatus(labTest.getStatus());
        dto.setPatientId(labTest.getPatient().getPatientId());
        dto.setPatientName(labTest.getPatient().getFirstName() + " " + labTest.getPatient().getLastName());
        dto.setDoctorId(labTest.getDoctor().getDoctorId());
        dto.setDoctorName(labTest.getDoctor().getFirstName() + " " + labTest.getDoctor().getLastName());
        dto.setCreatedBy(labReport.getCreatedBy());
        dto.setUpdatedBy(labReport.getUpdatedBy());
        dto.setCreatedAt(labReport.getCreatedAt());
        dto.setUpdatedAt(labReport.getUpdatedAt());
        return dto;
    }
    public List<LabReportResponseDto> toDtoList(List<LabReport> labtest)
    {
    	return labtest.stream().map(this::toDto).collect(Collectors.toList());
    }
}