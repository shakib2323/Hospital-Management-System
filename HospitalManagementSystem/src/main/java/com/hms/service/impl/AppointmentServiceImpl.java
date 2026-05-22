package com.hms.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hms.audit.AuditActionType;
import com.hms.audit.AuditLogService;
import com.hms.dto.AppointmentRequestDto;
import com.hms.dto.AppointmentResponseDto;
import com.hms.entity.Appointment;
import com.hms.entity.Doctor;
import com.hms.entity.Patient;
import com.hms.enums.AppointmentStatus;
import com.hms.exception.ResourceNotFoundException;
import com.hms.mapper.AppointmentMapper;
import com.hms.repository.AppointmentRepository;
import com.hms.repository.DepartmentRepository;
import com.hms.repository.DoctorRepository;
import com.hms.repository.PatientRepository;
import com.hms.service.AppointmentService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService
{
  private final AppointmentRepository appointmentRepo;
  private final AppointmentMapper appointmentMapper;
  private final DoctorRepository doctorRepo;
  private final PatientRepository patientRepo;
  private final AuditLogService auditLogService;
	@Override
	public AppointmentResponseDto bookAppointment(AppointmentRequestDto dto) {
		Appointment appointment=appointmentMapper.toEntity(dto);
		appointmentRepo.save(appointment);
		auditLogService.log(dto.getCreatedBy(), AuditActionType.CREATE.name(), "Book Appointment", "Appointment name: "+dto.getAppointmentNumber());
		return appointmentMapper.toDto(appointment);
	}

	@Override
	public AppointmentResponseDto rescheduleAppointment(Long appointmentId, AppointmentRequestDto dto) {
		Appointment appointment=appointmentRepo.findById(appointmentId).orElseThrow(()->
		new ResourceNotFoundException("Appointment not found with id: "+appointmentId));
		appointment.setAppointmentDate(dto.getAppointmentDate());
		appointment.setAppointmentTime(dto.getAppointmentTime());
		appointment.setRemarks(dto.getRemarks());
		appointment.setUpdatedBy(dto.getUpdatedBy());
//		Doctor doctor=doctorRepo.findById(dto.getDoctorId()).orElseThrow(()->
//		new ResourceNotFoundException("Doctor not found with id: " + dto.getDoctorId()));
//		appointment.setDoctor(doctor);
//		Patient patient=patientRepo.findById(dto.getPatientId()).orElseThrow(()->
//		new ResourceNotFoundException("Patient not found with id: "+dto.getPatientId()));
//		appointment.setPatient(patient);
//		Department department=departmentRepo.findById(dto.getDepartmentId()).orElseThrow(()->
//		new ResourceNotFoundException("Department not found with id: "+dto.getDepartmentId()));
//		appointment.setDepartment(department);
		appointment.setCreatedBy(dto.getCreatedBy());
		appointment.setUpdatedBy(dto.getUpdatedBy());
		appointmentRepo.save(appointment);
		return appointmentMapper.toDto(appointment);
	}

	@Override
	public AppointmentResponseDto cancelAppointment(Long appointmentId) {
		Appointment appointment=appointmentRepo.findById(appointmentId).orElseThrow(()->
		new ResourceNotFoundException("Appointment not found with id: "+appointmentId));
		appointment.setStatus(AppointmentStatus.CANCELLED);
		return appointmentMapper.toDto(appointment);
	}

	@Override
	public AppointmentResponseDto completeAppointment(Long appointmentId) {
		Appointment appointment=appointmentRepo.findById(appointmentId).orElseThrow(()->
		new ResourceNotFoundException("Appointment not found with id: "+appointmentId));
		appointment.setStatus(AppointmentStatus.COMPLETED);
		return appointmentMapper.toDto(appointment);
	}

	@Override
	public AppointmentResponseDto getAppointmentById(Long appointmentId) {
		Appointment appointment=appointmentRepo.findById(appointmentId).orElseThrow(()->
		new ResourceNotFoundException("Appointment not found with id: "+appointmentId));
		return appointmentMapper.toDto(appointment);
	}

	@Override
	public List<AppointmentResponseDto> getAllAppointments() {
		List<Appointment> appointment=appointmentRepo.findAll();
		return appointmentMapper.toDtoList(appointment);
	}

	@Override
	public List<AppointmentResponseDto> getAppointmentsByDoctor(Long doctorId) {
		Doctor doctor=doctorRepo.findById(doctorId).orElseThrow(()->
		new ResourceNotFoundException("Doctor appointment not found with id: "+doctorId));
		List<Appointment> appointment=appointmentRepo.findByDoctor(doctor);
		return appointmentMapper.toDtoList(appointment);
	}

	@Override
	public List<AppointmentResponseDto> getAppointmentsByPatient(Long patientId) {
		Patient patient=patientRepo.findById(patientId).orElseThrow(()->
		new ResourceNotFoundException("Patient not found with id: "+patientId));
		List<Appointment> appointment=appointmentRepo.findByPatient(patient);
		return appointmentMapper.toDtoList(appointment);
	}

	@Override
	public Integer generateTokenNumber(Long doctorId) {
		Doctor doctor=doctorRepo.findById(doctorId).orElseThrow(()->
		new ResourceNotFoundException("Doctor not found with id: "+doctorId));
		// Here first we are finding todays date to count todays appointment
		LocalDate date=LocalDate.now();
		//Here we are finding actual appointments of the day!
		List<Appointment> todayAppointments=appointmentRepo.findByDoctorAndAppointmentDate(doctor, date);
		// here we are generating and return token number!
		return todayAppointments.size()+1;
	}

	@Override
	public Boolean checkDoctorAvailability(Long doctorId, LocalDate date, LocalTime time) {
		Doctor doctor=doctorRepo.findById(doctorId).orElseThrow(()->
		new ResourceNotFoundException("Doctor not found with id: "+doctorId));
		LocalDate date1=LocalDate.now();
		LocalTime time1=LocalTime.now();
		Boolean exist=appointmentRepo.existsByDoctorAndAppointmentDateAndAppointmentTime(doctor, date1, time1);
		return !exist;
		
	}



}
