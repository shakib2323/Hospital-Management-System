package com.hms.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hms.audit.AuditLogService;
import com.hms.dto.AdmissionRequestDto;
import com.hms.dto.AdmissionResponseDto;
import com.hms.entity.Admission;
import com.hms.entity.Bed;
import com.hms.entity.Doctor;
import com.hms.entity.Patient;
import com.hms.enums.AdmissionStatus;
import com.hms.enums.BedStatus;
import com.hms.exception.ResourceNotFoundException;
import com.hms.mapper.AdmissionMapper;
import com.hms.repository.AdmissionRepository;
import com.hms.repository.BedRepository;
import com.hms.repository.DoctorRepository;
import com.hms.repository.PatientRepository;
import com.hms.service.AdmissionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdmissionServiceImpl implements AdmissionService {

    private final AdmissionRepository admissionRepo;
    private final PatientRepository patientRepo;
    private final DoctorRepository doctorRepo;
    private final BedRepository bedRepo;
    private final AdmissionMapper admissionMapper;
    private final AuditLogService auditLogService;  

    @Override
    public AdmissionResponseDto admitPatient(AdmissionRequestDto dto) {

        Patient patient = patientRepo.findById(dto.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + dto.getPatientId()));
        Doctor doctor = doctorRepo.findById(dto.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + dto.getDoctorId()));
        Bed bed = bedRepo.findById(dto.getBedId())
                .orElseThrow(() -> new ResourceNotFoundException( "Bed not found with id: " + dto.getBedId()));
        //Check bed is available before admitting
        if (!bed.getStatus().equals(BedStatus.AVAILABLE)) {
            throw new IllegalStateException("Bed is not available with id: " + dto.getBedId());
        }
        Admission admission = new Admission();
        admission.setAdmissionDate(dto.getAdmissionDate());
        admission.setDischargeDate(dto.getDischargeDate());
        admission.setAdmissionReason(dto.getAdmissionReason());
        admission.setAdmissionStatus(AdmissionStatus.ACTIVE);
        admission.setGuardianName(dto.getGuardianName());
        admission.setGuardianContact(dto.getGuardianContact());
        admission.setPatient(patient);
        admission.setDoctor(doctor);
        admission.setBed(bed);
        //Mark bed as occupied
        bed.setStatus(BedStatus.OCCUPIED);
        bedRepo.save(bed);
        Admission savedAdmission = admissionRepo.save(admission);
        // AuditLog
        auditLogService.log(
        		dto.getAdmissionReason(),
                "CREATE",
                "Admission",
                "Patient admitted with id: " + dto.getPatientId()+ " to bed id: " + dto.getBedId()
        );

        return admissionMapper.toDto(savedAdmission);
    }
    @Transactional
    @Override
    public AdmissionResponseDto dischargePatient(Long admissionId) {
        Admission admission = admissionRepo.findById(admissionId)
                .orElseThrow(() -> new ResourceNotFoundException("Admission not found with id: " + admissionId));

        //Fixed —comparing enum not String
        if (!admission.getAdmissionStatus().equals(AdmissionStatus.ACTIVE)) {
            throw new IllegalStateException("Patient is already discharged for admission id: " + admissionId);
        }
        admission.setDischargeDate(LocalDate.now());
        admission.setAdmissionStatus(AdmissionStatus.DISCHARGED);
        //Free the bed
        Bed bed = admission.getBed();
        bed.setStatus(BedStatus.AVAILABLE);
        bedRepo.save(bed);
        admissionRepo.save(admission);
        //AuditLog
        auditLogService.log(
                "admin",
                "DISCHARGE",
                "Admission",
                "Patient discharged for admission id: " + admissionId
        );
        return admissionMapper.toDto(admission);
    }

    @Override
    public AdmissionResponseDto transferPatient(Long admissionId, Long newBedId) {
        Admission admission = admissionRepo.findById(admissionId)
                .orElseThrow(() -> new ResourceNotFoundException("Admission not found with id: " + admissionId));
        //Fixedcomparing enum not String
        if (!admission.getAdmissionStatus().equals(AdmissionStatus.ACTIVE)) {
            throw new IllegalStateException("Cannot transfer patient is not actively admitted.");
        }
        Bed newBed = bedRepo.findById(newBedId)
                .orElseThrow(() -> new ResourceNotFoundException("Bed not found with id: " + newBedId));

        //Fixed comparing enum not String
        if (!newBed.getStatus().equals(BedStatus.AVAILABLE)) {
            throw new IllegalStateException("Bed is not available with id: " + newBedId);
        }
        //Free old bed
        Bed oldBed = admission.getBed();
        oldBed.setStatus(BedStatus.AVAILABLE);
        bedRepo.save(oldBed);
        // Assign new bed
        newBed.setStatus(BedStatus.OCCUPIED);
        bedRepo.save(newBed);

        admission.setBed(newBed);
        admission.setAdmissionStatus(AdmissionStatus.TRANSFERRED);
        admissionRepo.save(admission);
        //AuditLog
        auditLogService.log(
                "admin",
                "TRANSFER",
                "Admission",
                "Patient transferred to new bed id: " + newBedId+ " for admission id: " + admissionId
        );

        return admissionMapper.toDto(admission);
    }

    @Override
    public AdmissionResponseDto getAdmissionById(Long admissionId) {
        Admission admission = admissionRepo.findById(admissionId)
                .orElseThrow(() -> new ResourceNotFoundException("Admission not found with id: " + admissionId));
        return admissionMapper.toDto(admission);  // no log read only
    }

    @Override
    public List<AdmissionResponseDto> getActiveAdmissions() {
        List<Admission> admissions = admissionRepo
                .findByAdmissionStatus(AdmissionStatus.ACTIVE);
        return admissionMapper.toDtoList(admissions);           //no log read only
    }

    @Override
    public List<AdmissionResponseDto> getPatientAdmissions(Long patientId) {
        Patient patient = patientRepo.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + patientId));
        List<Admission> admissions = admissionRepo.findByPatient(patient);
        return admissionMapper.toDtoList(admissions);           //no log — read only
    }
}