package com.hms.service;

import com.hms.dto.MedicalRecordRequestDto;
import com.hms.dto.MedicalRecordResponseDto;

public interface MedicalRecordService 
{
	public MedicalRecordResponseDto createMedicalRecord(Long patientId,MedicalRecordRequestDto dto);
	public MedicalRecordResponseDto updateMedicalRecord(Long recordId,MedicalRecordRequestDto dto);
	public MedicalRecordResponseDto getMedicalRecordByPatient(Long patientId);
	public void deleteMedicalRecord(Long recordId);
}
