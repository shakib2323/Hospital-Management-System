package com.hms.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.hms.dto.PrescriptionRequestDto;
import com.hms.dto.PrescriptionResponseDto;
import com.hms.entity.Appointment;
import com.hms.entity.Doctor;
import com.hms.entity.Patient;
import com.hms.entity.Prescription;
import com.hms.exception.ResourceNotFoundException;
import com.hms.repository.AppointmentRepository;
import com.hms.repository.DoctorRepository;
import com.hms.repository.PatientRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PrescriptionMapper {

    private final DoctorRepository doctorRepo;
    private final PatientRepository patientRepo;
    private final AppointmentRepository appointmentRepo;
    public Prescription toEntity(PrescriptionRequestDto dto)
    {
        Prescription prescription = new Prescription();
        prescription.setDiagnosis(dto.getDiagnosis());
        prescription.setDoctorNotes(dto.getDoctorNotes());
        prescription.setFollowUpDate(dto.getFollowUpDate());
        prescription.setPrescribedDate(dto.getPrescribedDate());
        Appointment appointment = appointmentRepo.findById(dto.getAppointmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + dto.getAppointmentId()));
        prescription.setAppointment(appointment);          

        Doctor doctor = doctorRepo.findById(dto.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + dto.getDoctorId()));
        prescription.setDoctor(doctor);                 

        Patient patient = patientRepo.findById(dto.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + dto.getPatientId()));
        prescription.setPatient(patient);                

        prescription.setCreatedBy(dto.getCreatedBy());
        prescription.setUpdatedBy(dto.getUpdatedBy());
        return prescription;
    }

    public PrescriptionResponseDto toDto(Prescription prescription) 
    {
        PrescriptionResponseDto dto = new PrescriptionResponseDto();
        dto.setPrescriptionId(prescription.getPrescriptionId());
        dto.setDiagnosis(prescription.getDiagnosis());
        dto.setDoctorNotes(prescription.getDoctorNotes());
        dto.setFollowUpDate(prescription.getFollowUpDate());
        dto.setPrescribedDate(prescription.getPrescribedDate());
        if (prescription.getAppointment() != null) {
            dto.setAppointmentId(prescription.getAppointment().getAppointmentId());
        }
        if (prescription.getDoctor() != null) {
            dto.setDoctorId(prescription.getDoctor().getDoctorId());
            dto.setDoctorName(prescription.getDoctor().getFirstName()
                    + " " + prescription.getDoctor().getLastName());
        }
        if (prescription.getPatient() != null) {
            dto.setPatientId(prescription.getPatient().getPatientId());
            dto.setPatientName(prescription.getPatient().getFirstName()
                    + " " + prescription.getPatient().getLastName());
        }
        dto.setCreatedBy(prescription.getCreatedBy());
        dto.setUpdatedBy(prescription.getUpdatedBy());
        dto.setCreatedAt(prescription.getCreatedAt());
        dto.setUpdatedAt(prescription.getUpdatedAt());
        return dto;
    }

    public List<PrescriptionResponseDto> toDtoList(List<Prescription> prescriptions) 
    {
        return prescriptions.stream().map(this::toDto).collect(Collectors.toList());
    }
}