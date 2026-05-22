package com.hms.service;

import java.util.List;

import com.hms.dto.BedRequestDto;
import com.hms.dto.BedResponseDto;
import com.hms.dto.BedUpdateRequestDto;

public interface BedService 
{
	public BedResponseDto addBed(BedRequestDto dto);
	public BedResponseDto updateBed(Long bedId,BedUpdateRequestDto dto);
	public BedResponseDto assignBedToPatient(Long patientId, Long bedId);
	public BedResponseDto releaseBed(Long bedId);
	public List<BedResponseDto> getAvailableBeds();
	public List<BedResponseDto> getOccupiedBeds();
	public BedResponseDto getBedById(Long bedId);
}