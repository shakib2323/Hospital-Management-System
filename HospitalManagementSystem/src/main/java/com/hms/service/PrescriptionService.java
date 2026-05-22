package com.hms.service;

import java.util.List;

import com.hms.dto.PrescriptionRequestDto;
import com.hms.dto.PrescriptionResponseDto;
import com.hms.dto.PrescriptionUpdateRequestDto;

public interface PrescriptionService 
{
	public PrescriptionResponseDto createPrescription(PrescriptionRequestDto dto);
	public PrescriptionResponseDto updatePrescription(Long prescriptionId,PrescriptionUpdateRequestDto dto);
	public PrescriptionResponseDto getPrescriptionById(Long prescriptionId);
	public List<PrescriptionResponseDto>getPatientPrescriptions(Long patientId);
	public void addMedicineToPrescription(Long prescriptionId,Long medicineId);
	public void removePrescription(Long prescriptionId);
}
