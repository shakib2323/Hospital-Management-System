package com.hms.service;

import java.util.List;

import com.hms.dto.AmbulanceRequestDto;
import com.hms.dto.AmbulanceResponseDto;

public interface AmbulanceService 
{
   public AmbulanceResponseDto registerAmbulance(AmbulanceRequestDto dto);
   public AmbulanceResponseDto updateAmbulance(Long ambulanceId, AmbulanceRequestDto dto);
   public AmbulanceResponseDto assignAmbulance(Long patientId, Long ambulanceId);
   public List<AmbulanceResponseDto> getAvailableAmbulances();
   public AmbulanceResponseDto trackAmbulance(Long ambulanceId);
   public void removeAmbulance(Long ambulanceId);
}
