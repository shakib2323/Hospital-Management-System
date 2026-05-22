package com.hms.service;

import java.util.List;

import com.hms.dto.LabTestRequestDto;
import com.hms.dto.LabTestResponseDto;

public interface LabTestService
{
	public LabTestResponseDto createLabTest(LabTestRequestDto dto);
	public LabTestResponseDto updateLabTest(Long labTestId,LabTestRequestDto dto);
	public LabTestResponseDto getLabTestById(Long labTestId);
	public List<LabTestResponseDto> getAllLabTests();
	public void assignLabTechnician(Long testId,String technicianName);
	public void cancelLabTest(Long testId);
}