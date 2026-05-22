package com.hms.service;

import java.util.List;

import com.hms.dto.SpecializationRequestDto;
import com.hms.dto.SpecializationResponseDto;

public interface SpecializationService
{
	public SpecializationResponseDto addSpecialization(SpecializationRequestDto dto);
	public List<SpecializationResponseDto>getAllSpecializations();
	public void assignDoctorToSpecialization(Long doctorId,Long specializationId);
	public void removeSpecialization(Long specializationId);
}
