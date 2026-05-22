package com.hms.service;

import java.util.List;

import com.hms.dto.AppointmentResponseDto;
import com.hms.dto.DoctorRequestDto;
import com.hms.dto.DoctorResponseDto;
import com.hms.dto.DoctorUpdateRequestDto;

public interface DoctorService 
{
	public DoctorResponseDto registerDoctor(DoctorRequestDto dto);
	public DoctorResponseDto updateDoctor(Long doctorId,DoctorUpdateRequestDto dto);
	public DoctorResponseDto getDoctorById(Long doctorId);
	public List<DoctorResponseDto> getAllDoctors();
	public List<DoctorResponseDto> searchDoctorsBySpecialization(String specialization);
	public List<DoctorResponseDto> getAvailableDoctors();
	public List<AppointmentResponseDto> getDoctorAppointments(Long doctorId);
	public void assignSpecialization(Long doctorId, Long specializationId);
	public void removeDoctor(Long doctorId);
}
