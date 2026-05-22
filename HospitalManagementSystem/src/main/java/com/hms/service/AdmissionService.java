package com.hms.service;

import java.util.List;

import com.hms.dto.AdmissionRequestDto;
import com.hms.dto.AdmissionResponseDto;

public interface AdmissionService
{
   public AdmissionResponseDto admitPatient(AdmissionRequestDto dto);
   public AdmissionResponseDto dischargePatient( Long admissionId);
   public AdmissionResponseDto transferPatient( Long admissionId,Long newBedId);
   public AdmissionResponseDto getAdmissionById(Long admissionId);
   public List<AdmissionResponseDto>getActiveAdmissions();
   public List<AdmissionResponseDto>getPatientAdmissions(Long patientId);
}