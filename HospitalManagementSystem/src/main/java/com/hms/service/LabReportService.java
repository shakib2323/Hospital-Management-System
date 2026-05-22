package com.hms.service;

import java.util.List;

import com.hms.dto.LabReportRequestDto;
import com.hms.dto.LabReportResponseDto;

public interface LabReportService 
 {
	public LabReportResponseDto generateLabReport(LabReportRequestDto dto);
	public LabReportResponseDto updateLabReport(Long reportId,LabReportRequestDto dto);
	public LabReportResponseDto getReportById(Long reportId);
	public List<LabReportResponseDto>getPatientReports(Long patientId);
	public void approveReport(Long reportId);
 }