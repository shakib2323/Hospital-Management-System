package com.hms.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hms.dto.LabReportRequestDto;
import com.hms.dto.LabReportResponseDto;
import com.hms.entity.LabReport;
import com.hms.entity.LabTest;
import com.hms.entity.Patient;
import com.hms.enums.LabTestStatus;
import com.hms.exception.ResourceNotFoundException;
import com.hms.mapper.LabReportMapper;
import com.hms.repository.LabReportRepository;
import com.hms.repository.LabTestRepository;
import com.hms.repository.PatientRepository;
import com.hms.service.LabReportService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class LabReportServiceIMpl implements LabReportService
{
    private final LabReportRepository labReportRepo;
    private final LabReportMapper labReportMapper;
    private final PatientRepository patientRepo;
    private final LabTestRepository labTestRepo;
	@Override
	public LabReportResponseDto generateLabReport(LabReportRequestDto dto) {
		LabReport labreport=labReportMapper.toEntity(dto);
		labReportRepo.save(labreport);
		return labReportMapper.toDto(labreport);
	}

	@Override
	public LabReportResponseDto updateLabReport(Long reportId, LabReportRequestDto dto) {
		LabReport labreport=labReportRepo.findById(reportId).orElseThrow(()->
		new ResourceNotFoundException("LabReport not found with id: "+reportId));
		labReportMapper.updateEntity(labreport, dto);
		labReportRepo.save(labreport);
		return labReportMapper.toDto(labreport) ;
	}

	@Override
	public LabReportResponseDto getReportById(Long reportId) {
		LabReport labreport=labReportRepo.findById(reportId).orElseThrow(()->
		new ResourceNotFoundException("LabReport not found with id: "+reportId));
		return labReportMapper.toDto(labreport);
	}

	@Override
	public List<LabReportResponseDto> getPatientReports(Long patientId) {
	    Patient patient = patientRepo.findById(patientId)
	            .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + patientId));
	    List<LabTest> labTests = labTestRepo.findByPatient(patient);
	    List<LabReport> reports = labTests.stream().map(labTest -> labReportRepo.findByLabTest(labTest))
	            .filter(report -> report != null)
	            .collect(Collectors.toList());

	    return labReportMapper.toDtoList(reports);
	}

	@Override
	public void approveReport(Long reportId) {
	    LabReport report = labReportRepo.findById(reportId)
	            .orElseThrow(() -> new ResourceNotFoundException("Lab report not found with id: " + reportId));
	    if (report.getApprovedBy() != null) {
	        throw new IllegalStateException("Report is already approved with id: " + reportId);
	    }

	    //Set approved by and update lab test status to COMPLETED
	    report.setApprovedBy("admin"); //replace with logged-in user later
	    report.getLabTest().setStatus(LabTestStatus.COMPLETED);
	    labTestRepo.save(report.getLabTest());
	    labReportRepo.save(report);
	}

}
