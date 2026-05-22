package com.hms.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.hms.enums.AppointmentStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AppointmentRequestDto 
{
    @NotBlank(message = "Appointment number is required!")
    private String appointmentNumber;
    @NotNull(message = "Appointment date is required!")
    private LocalDate appointmentDate;
    @NotNull(message = "Appointment time is required!")
    private LocalTime appointmentTime;
    @NotBlank(message = "Symptoms are required!")
    @Size(max = 1000, message = "Symptoms must not exceed 1000 characters!")
    private String symptoms;
    @NotBlank(message = "Priority level is required!")
    private String priorityLevel;
    @NotNull(message = "Token number is required!")
    private Integer tokenNumber;
    @NotNull(message = "Status is required!")
    private AppointmentStatus status;
    @Size(max = 2000, message = "Remarks must not exceed 2000 characters!")
    private String remarks;
    @NotNull(message = "Doctor is required!")
    private Long doctorId;       
    @NotNull(message = "Patient is required!")
    private Long patientId;      
    @NotNull(message = "Department is required!")
    private Long departmentId;   
    @NotBlank(message = "Administrator name is required.")
    private String createdBy;
    private String updatedBy;
}