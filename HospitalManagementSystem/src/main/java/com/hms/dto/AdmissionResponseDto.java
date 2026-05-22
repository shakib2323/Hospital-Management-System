package com.hms.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.hms.enums.AdmissionStatus;

import lombok.Data;

@Data
public class AdmissionResponseDto
{
	private Long admissionId; 
    private LocalDate admissionDate;
    private LocalDate dischargeDate;
    private String admissionReason;
    private AdmissionStatus admissionStatus;
    private String guardianName;
    private String guardianContact;
    private Long patientId;
    private Long doctorId;
    private Long bedId;
    private String patientName;         
    private String doctorName;          
    private String bedNumber;  
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
}
