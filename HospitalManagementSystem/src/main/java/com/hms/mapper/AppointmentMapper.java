package com.hms.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.hms.dto.AppointmentRequestDto;
import com.hms.dto.AppointmentResponseDto;
import com.hms.entity.Appointment;
import com.hms.entity.Department;
import com.hms.entity.Doctor;
import com.hms.entity.Patient;
import com.hms.exception.ResourceNotFoundException;
import com.hms.repository.DepartmentRepository;
import com.hms.repository.DoctorRepository;
import com.hms.repository.PatientRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AppointmentMapper 
{
    private final DoctorRepository doctorRepo;
    private final PatientRepository patientRepo;
    private final DepartmentRepository departmentRepo;
	public Appointment toEntity(AppointmentRequestDto dto)
	{
		Appointment appointment=new Appointment();
		appointment.setAppointmentNumber(dto.getAppointmentNumber());
		appointment.setAppointmentDate(dto.getAppointmentDate());
		appointment.setAppointmentTime(dto.getAppointmentTime());
		appointment.setSymptoms(dto.getSymptoms());
		appointment.setPriorityLevel(dto.getPriorityLevel());
		appointment.setTokenNumber(dto.getTokenNumber());
		appointment.setStatus(dto.getStatus());
		appointment.setRemarks(dto.getRemarks());
		Doctor doctor=doctorRepo.findById(dto.getDoctorId()).orElseThrow(()->
		new ResourceNotFoundException("Doctor not found with id: " + dto.getDoctorId()));
		appointment.setDoctor(doctor);
		Patient patient=patientRepo.findById(dto.getPatientId()).orElseThrow(()->
		new ResourceNotFoundException("Patient not found with id: "+dto.getPatientId()));
		appointment.setPatient(patient);
		Department department=departmentRepo.findById(dto.getDepartmentId()).orElseThrow(()->
		new ResourceNotFoundException("Department not found with id: "+dto.getDepartmentId()));
		appointment.setDepartment(department);
		appointment.setCreatedBy(dto.getCreatedBy());
		appointment.setUpdatedBy(dto.getUpdatedBy());
		return appointment;
	}
	public AppointmentResponseDto toDto(Appointment appointment)
	{
	    AppointmentResponseDto dto = new AppointmentResponseDto();
	    dto.setAppointmentId(appointment.getAppointmentId());      
	    dto.setAppointmentNumber(appointment.getAppointmentNumber());
	    dto.setAppointmentDate(appointment.getAppointmentDate());
	    dto.setAppointmentTime(appointment.getAppointmentTime());
	    dto.setSymptoms(appointment.getSymptoms());
	    dto.setPriorityLevel(appointment.getPriorityLevel());
	    dto.setTokenNumber(appointment.getTokenNumber());
	    dto.setStatus(appointment.getStatus());
	    dto.setRemarks(appointment.getRemarks());
	    // Doctor info
	    dto.setDoctorId(appointment.getDoctor().getDoctorId());
	    dto.setDoctorName(appointment.getDoctor().getFirstName()+ " " + appointment.getDoctor().getLastName());
	    // Patient info
	    dto.setPatientId(appointment.getPatient().getPatientId());
	    dto.setPatientName(appointment.getPatient().getFirstName()+ " " + appointment.getPatient().getLastName());
	    dto.setDepartmentId(appointment.getDepartment().getDepartmentId());
	    dto.setDepartmentName(appointment.getDepartment().getDepartmentName());
	    dto.setCreatedBy(appointment.getCreatedBy());
	    dto.setUpdatedBy(appointment.getUpdatedBy());
	    dto.setCreatedAt(appointment.getCreatedAt());             
	    dto.setUpdatedAt(appointment.getUpdatedAt());             
	    return dto;
	}
	public List<AppointmentResponseDto> toDtoList(List<Appointment> appointment)
	{
		return appointment.stream().map(this::toDto).collect(Collectors.toList());
	}
}
