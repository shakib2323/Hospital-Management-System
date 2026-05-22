package com.hms.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.hms.enums.AppointmentStatus;

import lombok.Data;
@Data
public class AppointmentResponseDto 
{
	    private Long appointmentId;
	    private String appointmentNumber;
	    private LocalDate appointmentDate;
	    private LocalTime appointmentTime;
	    private String symptoms;
	    private String priorityLevel;
	    private Integer tokenNumber;
	    private AppointmentStatus status;
	    private String remarks;
	    private Long doctorId;       
	    private Long patientId;      
	    private Long departmentId;   
	    private String createdBy;
	    private String updatedBy;
	    private LocalDateTime createdAt;
	    private LocalDateTime updatedAt;
	    private String doctorName;
	    private String departmentName;
	    private String patientName;
}
