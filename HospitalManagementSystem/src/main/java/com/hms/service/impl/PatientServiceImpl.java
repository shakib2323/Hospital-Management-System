package com.hms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hms.dto.AppointmentResponseDto;
import com.hms.dto.BillingResponseDto;
import com.hms.dto.MedicalRecordResponseDto;
import com.hms.dto.PatientRequestDto;
import com.hms.dto.PatientResponseDto;
import com.hms.dto.PatientUpdateRequestDto;
import com.hms.entity.Appointment;
import com.hms.entity.Patient;
import com.hms.exception.ResourceNotFoundException;
import com.hms.mapper.PatientMapper;
import com.hms.repository.AppointmentRepository;
import com.hms.repository.PatientRepository;
import com.hms.service.PatientService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService
{
    private final PatientRepository patientRepo;
    private final PatientMapper patientMapper;
//    private final AppointmentRepository appointRepo;
	@Override
	public PatientResponseDto registerPatient(PatientRequestDto dto) {
		Patient patient=patientMapper.toEntity(dto);
		patientRepo.save(patient);
		return patientMapper.toDto(patient);
	}

	@Override
	public PatientResponseDto updatePatient(Long patientId, PatientUpdateRequestDto dto) {
		Patient patient=patientRepo.findById(patientId).orElseThrow(()->
		new ResourceNotFoundException("Invalid Id: "+patientId));
		patient.setFirstName(dto.getFirstName());
		patient.setLastName(dto.getLastName());
		patient.setEmail(dto.getEmail());
		patient.setPhoneNumber(dto.getPhoneNumber());
		patient.setGender(dto.getGender());
		patient.setDateOfBirth(dto.getDateOfBirth());
		patient.setBloodGroup(dto.getBloodGroup());
		patient.setMaritalStatus(dto.getMaritalStatus());
		patient.setOccupation(dto.getOccupation());
		patient.setHeight(dto.getHeight());
		patient.setWeight(dto.getWeight());
		patient.setAllergies(dto.getAllergies());
		patient.setEmergencyContactName(dto.getEmergencyContactName());
		patient.setEmergencyContactNumber(dto.getEmergencyContactNumber());
		patient.setInsuranceProvider(dto.getInsuranceProvider());
		patient.setInsurancePolicyNumber(dto.getInsurancePolicyNumber());
		patient.setAddress(dto.getAddress());
		patient.setCity(dto.getCity());
		patient.setState(dto.getState());
		patient.setPincode(dto.getPincode());
		patientRepo.save(patient);
		return patientMapper.toDto(patient);
	}

	@Override
	public PatientResponseDto getPatientById(Long patientId) {
		Patient patient=patientRepo.findById(patientId).orElseThrow(()->
		new ResourceNotFoundException("Invalid Patient Id!"+patientId));
		return patientMapper.toDto(patient);
	}

	@Override
	public List<PatientResponseDto> getAllPatients() {
		List<Patient> patients=patientRepo.findAll();
		return patientMapper.toDtoList(patients);
	}

	@Override
	public MedicalRecordResponseDto getMedicalRecord(Long patientId) {
		Patient patient=patientRepo.findById(patientId).orElseThrow(()->
		new ResourceNotFoundException("Invalid Patient Id!"+patientId));
		return null;
	}

	@Override
	public List<AppointmentResponseDto> getPatientAppointments(Long patientId) {
//		Patient patient=patientRepo.findById(patientId).orElseThrow(()->
//		new ResourceNotFoundException("Invalid Patient Id!"+patientId));
//		 List<Appointment> appointments = appointRepo.findByPatient(patient);
//		    return appointmentMapper.toDtoList(appointments);
		return null;
	}

	@Override
	public List<BillingResponseDto> getPatientBills(Long patientId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deactivatePatient(Long patientId) {
		Patient patient=patientRepo.findById(patientId).orElseThrow(()->
		new ResourceNotFoundException("Invalid Patient Id!"+patientId));
		patientRepo.delete(patient);
		
	}

}
