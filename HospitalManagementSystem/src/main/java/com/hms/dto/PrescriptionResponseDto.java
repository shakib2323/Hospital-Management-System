package com.hms.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PrescriptionResponseDto
{
    private Long prescriptionId;
    private String diagnosis;
    private String doctorNotes;
    private LocalDate followUpDate;
    private LocalDate prescribedDate;
    private Long appointmentId;
    private Long doctorId;
    private String doctorName;      
    private Long patientId;
    private String patientName;    
    private LocalDateTime createdAt;  
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
}