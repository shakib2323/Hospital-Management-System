package com.hms.service;

import java.util.List;

import com.hms.dto.AppointmentResponseDto;
import com.hms.dto.BillingResponseDto;
import com.hms.dto.MedicalRecordResponseDto;
import com.hms.dto.PatientRequestDto;
import com.hms.dto.PatientResponseDto;
import com.hms.dto.PatientUpdateRequestDto;

public interface PatientService 
{
   public PatientResponseDto registerPatient(PatientRequestDto dto);
   public PatientResponseDto updatePatient(Long patientId,PatientUpdateRequestDto dto);
   public PatientResponseDto getPatientById(Long patientId);
   public List<PatientResponseDto> getAllPatients();
   public MedicalRecordResponseDto getMedicalRecord(Long patientId);
   public List<AppointmentResponseDto> getPatientAppointments( Long patientId);
   public List<BillingResponseDto> getPatientBills(Long patientId);
   public void deactivatePatient(Long patientId);
}
