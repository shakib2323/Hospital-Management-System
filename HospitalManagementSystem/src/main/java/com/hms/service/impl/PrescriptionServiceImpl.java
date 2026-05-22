package com.hms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hms.dto.PrescriptionRequestDto;
import com.hms.dto.PrescriptionResponseDto;
import com.hms.dto.PrescriptionUpdateRequestDto;
import com.hms.entity.Medicine;
import com.hms.entity.Patient;
import com.hms.entity.Prescription;
import com.hms.exception.ResourceNotFoundException;
import com.hms.mapper.PrescriptionMapper;
import com.hms.repository.MedicineRepository;
import com.hms.repository.PatientRepository;
import com.hms.repository.PrescriptionRepository;
import com.hms.service.PrescriptionService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class PrescriptionServiceImpl implements PrescriptionService
{
	private final PrescriptionRepository prescriptionRepo;
//	private final DoctorRepository doctorRepo;
	private final PatientRepository patientRepo;
	private final PrescriptionMapper prescriptionMapper;
	private final MedicineRepository medicineRepo;

	@Override
	public PrescriptionResponseDto createPrescription(PrescriptionRequestDto dto) {
		Prescription prescription=prescriptionMapper.toEntity(dto);
		prescriptionRepo.save(prescription);
		return prescriptionMapper.toDto(prescription);
	}

	@Override
	public PrescriptionResponseDto updatePrescription(Long prescriptionId, PrescriptionUpdateRequestDto dto) {
		Prescription prescription=prescriptionRepo.findById(prescriptionId).orElseThrow(()->
		new ResourceNotFoundException("Prescription not found with id: "+prescriptionId));
		prescription.setDiagnosis(dto.getDiagnosis());
		prescription.setDoctorNotes(dto.getDoctorNotes());
		prescription.setFollowUpDate(dto.getFollowUpDate());
		prescription.setPrescribedDate(dto.getPrescribedDate());
		prescription.setUpdatedBy(dto.getUpdatedBy());
		prescriptionRepo.save(prescription);
		
		return prescriptionMapper.toDto(prescription);
	}

	@Override
	public PrescriptionResponseDto getPrescriptionById(Long prescriptionId) {
		Prescription prescription=prescriptionRepo.findById(prescriptionId).orElseThrow(()->
		new ResourceNotFoundException("Prescription not found with id: "+prescriptionId));
		return prescriptionMapper.toDto(prescription);
	}

	@Override
	public List<PrescriptionResponseDto> getPatientPrescriptions(Long patientId) {
		Patient patient=patientRepo.findById(patientId).orElseThrow(()->
		new ResourceNotFoundException("Patient not found with id: "+patientId));
		List<Prescription> patientPrescription=prescriptionRepo.findByPatient(patient);
		return prescriptionMapper.toDtoList(patientPrescription);
	}

	@Override
	public void addMedicineToPrescription(Long prescriptionId, Long medicineId) {
		Prescription prescription=prescriptionRepo.findById(prescriptionId).orElseThrow(()->
		new ResourceNotFoundException("Prescription not found with id: "+prescriptionId));
		Medicine medicine=medicineRepo.findById(medicineId).orElseThrow(()->
		new ResourceNotFoundException("Medicine not found with id: "+medicineId));

	    List<Medicine> medicines = prescription.getMedicines();
	    
	    if (medicines == null) {
	        medicines = new ArrayList<>();
	    }
	    medicines.add(medicine);
	    prescription.setMedicines(medicines);
	    prescriptionRepo.save(prescription);
	}

	@Override
	public void removePrescription(Long prescriptionId) {
		Prescription prescription=prescriptionRepo.findById(prescriptionId).orElseThrow(()->
		new ResourceNotFoundException("Prescription not found with id: "+prescriptionId));
		prescriptionRepo.delete(prescription);
		
	}

}
