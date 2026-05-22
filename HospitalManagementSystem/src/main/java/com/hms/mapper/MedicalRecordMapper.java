package com.hms.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.hms.dto.MedicalRecordRequestDto;
import com.hms.dto.MedicalRecordResponseDto;
import com.hms.entity.MedicalRecord;
import com.hms.entity.Patient;
import com.hms.exception.ResourceNotFoundException;
import com.hms.repository.PatientRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MedicalRecordMapper
{
    private final PatientRepository patientRepo;
    public MedicalRecord toEntity(MedicalRecordRequestDto dto)
    {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setChronicDiseases(dto.getChronicDiseases());
        medicalRecord.setPastSurgeries(dto.getPastSurgeries());
        medicalRecord.setFamilyMedicalHistory(dto.getFamilyMedicalHistory());
        medicalRecord.setCurrentMedications(dto.getCurrentMedications());
        medicalRecord.setVaccinationDetails(dto.getVaccinationDetails());
        medicalRecord.setSmokingHabit(dto.getSmokingHabit());
        medicalRecord.setAlcoholConsumption(dto.getAlcoholConsumption());
        medicalRecord.setOrganDonor(dto.getOrganDonor());
        medicalRecord.setRemarks(dto.getRemarks());
        Patient patient = patientRepo.findById(dto.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Patient not found with id: " + dto.getPatientId()));
        medicalRecord.setPatient(patient);
        return medicalRecord;
    }

    public MedicalRecordResponseDto toDto(MedicalRecord medicalRecord)
    {
        MedicalRecordResponseDto dto = new MedicalRecordResponseDto();
        dto.setMedicalRecordId(medicalRecord.getMedicalRecordId());
        dto.setChronicDiseases(medicalRecord.getChronicDiseases());
        dto.setPastSurgeries(medicalRecord.getPastSurgeries());
        dto.setFamilyMedicalHistory(medicalRecord.getFamilyMedicalHistory());
        dto.setCurrentMedications(medicalRecord.getCurrentMedications());
        dto.setVaccinationDetails(medicalRecord.getVaccinationDetails());
        dto.setSmokingHabit(medicalRecord.getSmokingHabit());
        dto.setAlcoholConsumption(medicalRecord.getAlcoholConsumption());
        dto.setOrganDonor(medicalRecord.getOrganDonor());
        dto.setRemarks(medicalRecord.getRemarks());
        dto.setPatientId(medicalRecord.getPatient().getPatientId());
        dto.setPatientName(medicalRecord.getPatient().getFirstName()
                + " " + medicalRecord.getPatient().getLastName());
        dto.setCreatedBy(medicalRecord.getCreatedBy());
        dto.setUpdatedBy(medicalRecord.getUpdatedBy());
        dto.setCreatedAt(medicalRecord.getCreatedAt());
        dto.setUpdatedAt(medicalRecord.getUpdatedAt());
        return dto;
    }

    public List<MedicalRecordResponseDto> toDtoList(List<MedicalRecord> records) 
    {
        return records.stream().map(this::toDto).collect(Collectors.toList());
    }
}