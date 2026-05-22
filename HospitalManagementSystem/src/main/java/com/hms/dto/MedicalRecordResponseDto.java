package com.hms.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MedicalRecordResponseDto 
{
    private Long medicalRecordId;
    private String chronicDiseases;
    private String pastSurgeries;
    private String familyMedicalHistory;
    private String currentMedications;
    private String vaccinationDetails;
    private Boolean smokingHabit;
    private Boolean alcoholConsumption;
    private Boolean organDonor;
    private String remarks;
    private Long patientId;     
    private String patientName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
}