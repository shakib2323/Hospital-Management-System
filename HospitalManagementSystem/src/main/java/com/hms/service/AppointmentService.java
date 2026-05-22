package com.hms.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.hms.dto.AppointmentRequestDto;
import com.hms.dto.AppointmentResponseDto;

public interface AppointmentService 
{
   public AppointmentResponseDto bookAppointment(AppointmentRequestDto dto);
   public AppointmentResponseDto rescheduleAppointment(Long appointmentId,AppointmentRequestDto dto);
   public AppointmentResponseDto cancelAppointment(Long appointmentId);
   public AppointmentResponseDto completeAppointment(Long appointmentId);
   public AppointmentResponseDto getAppointmentById(Long appointmentId);
   public List<AppointmentResponseDto>getAllAppointments();
   public List<AppointmentResponseDto>getAppointmentsByDoctor(Long doctorId);
   public List<AppointmentResponseDto>getAppointmentsByPatient(Long patientId);
   public Integer generateTokenNumber(Long doctorId);
   public Boolean checkDoctorAvailability(Long doctorId,LocalDate date,LocalTime time);
}