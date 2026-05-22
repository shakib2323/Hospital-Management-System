package com.hms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hms.dto.AppointmentResponseDto;
import com.hms.dto.DoctorRequestDto;
import com.hms.dto.DoctorResponseDto;
import com.hms.dto.DoctorUpdateRequestDto;
import com.hms.entity.Appointment;
import com.hms.entity.Doctor;
import com.hms.entity.Specialization;
import com.hms.enums.DoctorAvailabilityStatus;
import com.hms.exception.ResourceNotFoundException;
import com.hms.mapper.AppointmentMapper;
import com.hms.mapper.DoctorMapper;
import com.hms.repository.AppointmentRepository;
import com.hms.repository.DoctorRepository;
import com.hms.repository.SpecializationRepository;
import com.hms.service.DoctorService;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

	private final DoctorRepository doctorRepo;
	private final DoctorMapper doctorMapper;
	private final SpecializationRepository specializationRepo;
	private final AppointmentRepository appointmentRepo;  
	private final AppointmentMapper appointmentMapper;     
	@Override
	public DoctorResponseDto registerDoctor(DoctorRequestDto dto) {
		Doctor doctor=doctorMapper.toEntity(dto);
		doctorRepo.save(doctor);
		return doctorMapper.toDto(doctor);
	}

	@Override
	public DoctorResponseDto updateDoctor(Long doctorId, DoctorUpdateRequestDto dto) {
		Doctor doctor=doctorRepo.findById(doctorId).orElseThrow(()->
		new ResourceNotFoundException("Invalid DoctorId!: "+doctorId));
		Map<String,String>map=new HashMap<>();
		if(doctor.getDoctorId()!=null)
		{
		map.put("DoctorId:", "Not Updatable Fields!"); 
		}
		if(doctor.getDoctorCode()!=null)
		{
			map.put("DoctorCode:", "Not Updatable fields!");
		}
		doctor.setFirstName(dto.getFirstName());
		doctor.setLastName(dto.getLastName());
		doctor.setEmail(dto.getEmail());
		doctor.setPhoneNumber(dto.getPhoneNumber());
		doctor.setGender(dto.getGender());
		doctor.setDateOfBirth(dto.getDateOfBirth());
		doctor.setQualification(dto.getQualification());
		doctor.setExperienceYears(dto.getExperienceYears());
		doctor.setConsultationFee(dto.getConsultationFee());
		doctor.setLicenseNumber(dto.getLicenseNumber());
		doctor.setSpecialNote(dto.getSpecialNote());
		doctor.setAvailabilityStartTime(dto.getAvailabilityStartTime());
		doctor.setAvailabilityEndTime(dto.getAvailabilityEndTime());
		doctor.setStatus(dto.getStatus());
		doctor.setUpdatedBy(dto.getUpdatedBy());
		doctorRepo.save(doctor);
		return doctorMapper.toDto(doctor);
	}

	@Override
	public DoctorResponseDto getDoctorById(Long doctorId) {
		Doctor doctor=doctorRepo.findById(doctorId).orElseThrow(()->
		new ResourceNotFoundException("Doctor not found for this id: "+doctorId));
		return doctorMapper.toDto(doctor);
	}

	@Override
	public List<DoctorResponseDto> getAllDoctors() {
		List<Doctor> doctors=doctorRepo.findAll();
		return doctorMapper.toDtoList(doctors);
	}

	@Override
	public List<DoctorResponseDto> searchDoctorsBySpecialization(String specialization)
	{
		List<Doctor> doctor=doctorRepo.findDoctorsBySpecialization(specialization);
		if(doctor.isEmpty())
		{
			throw new ResourceNotFoundException("Doctor not found for this: "+specialization);
		}
		return doctorMapper.toDtoList(doctor);
	}

	@Override
	public List<DoctorResponseDto> getAvailableDoctors() {
		List<Doctor> doctor=doctorRepo.findByStatus(DoctorAvailabilityStatus.AVAILABLE);
		
		return doctorMapper.toDtoList(doctor);
	}

	@Override
	public void assignSpecialization(Long doctorId, Long specializationId) {
		Doctor doctor=doctorRepo.findById(doctorId).orElseThrow(()->
		new ResourceNotFoundException("Doctor not found for this id: "+doctorId));
		Specialization specialization=specializationRepo.findById(specializationId).orElseThrow(()->
		new ResourceNotFoundException("Specialization  not found for this id: "+specializationId));
		 if (doctor.getSpecializations().contains(specialization))
		 {
		        throw new IllegalStateException("Specialization already assigned to this doctor!");
		 }
		doctor.getSpecializations().add(specialization);
		doctorRepo.save(doctor);
		
	}

	@Override
	public void removeDoctor(Long doctorId) {
		Doctor doctor=doctorRepo.findById(doctorId).orElseThrow(()->
		new ResourceNotFoundException("Doctor not found for this id: "+doctorId));
		doctorRepo.delete(doctor);
	}
	@Override
	public List<AppointmentResponseDto> getDoctorAppointments(Long doctorId) {
	    Doctor doctor = doctorRepo.findById(doctorId)
	            .orElseThrow(() -> new ResourceNotFoundException(
	                    "Doctor not found for this id: " + doctorId));

	    List<Appointment> appointments = appointmentRepo.findByDoctor(doctor);
	    return appointmentMapper.toDtoList(appointments);
	}
}
