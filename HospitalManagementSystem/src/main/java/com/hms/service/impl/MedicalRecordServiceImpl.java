package com.hms.service.impl;

import org.springframework.stereotype.Service;

import com.hms.dto.MedicalRecordRequestDto;
import com.hms.dto.MedicalRecordResponseDto;
import com.hms.entity.MedicalRecord;
import com.hms.entity.Patient;
import com.hms.exception.ResourceNotFoundException;
import com.hms.mapper.MedicalRecordMapper;
import com.hms.repository.MedicalRecordRepository;
import com.hms.repository.PatientRepository;
import com.hms.service.MedicalRecordService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class MedicalRecordServiceImpl implements MedicalRecordService
{
   private final MedicalRecordRepository medicalrecordRepo;
   private final MedicalRecordMapper medicalrecordMapper;
   private final PatientRepository patientRepo;
   
   @Override
   public MedicalRecordResponseDto createMedicalRecord(Long patientId, MedicalRecordRequestDto dto) {
       Patient patient = patientRepo.findById(patientId)
               .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + patientId));

       MedicalRecord medicalRecord = medicalrecordMapper.toEntity(dto);
       medicalRecord.setPatient(patient); 
       medicalrecordRepo.save(medicalRecord);
       return medicalrecordMapper.toDto(medicalRecord);
   }

	@Override
	public MedicalRecordResponseDto updateMedicalRecord(Long recordId, MedicalRecordRequestDto dto) {
		// TODO Auto-generated method stubreturn null;
		return null;
	}

	@Override
	public MedicalRecordResponseDto getMedicalRecordByPatient(Long patientId) {
		MedicalRecord medicalRecord=medicalrecordRepo.findById(patientId)
		  .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + patientId));
		return medicalrecordMapper.toDto(medicalRecord);
	}

	@Override
	public void deleteMedicalRecord(Long recordId) {
		MedicalRecord medicalRecord=medicalrecordRepo.findById(recordId)
				  .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + recordId));
		medicalrecordRepo.delete(medicalRecord);
		
	}

}
