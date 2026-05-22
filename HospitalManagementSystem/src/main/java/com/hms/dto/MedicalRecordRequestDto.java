package com.hms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MedicalRecordRequestDto
{
    @NotBlank(message = "Chronic diseases is required!")
    @Size(max = 1000, message = "Chronic diseases must not exceed 1000 characters!")
    private String chronicDiseases;
    @NotBlank(message = "Past surgeries is required")
    @Size(max = 1000, message = "Past surgeries must not exceed 1000 characters!")
    private String pastSurgeries;
    @NotBlank(message = "Family medical history is required")
    @Size(max = 1000, message = "Family medical history must not exceed 1000 characters!")
    private String familyMedicalHistory;
    @NotBlank(message = "Current medications is required")
    @Size(max = 1000, message = "Current medications must not exceed 1000 characters!")
    private String currentMedications;
    @NotBlank(message = "Vaccination details is required")
    @Size(max = 1000, message = "Vaccination details must not exceed 1000 characters!")
    private String vaccinationDetails;
    @NotNull(message = "Smoking habit is required!")      
    private Boolean smokingHabit;
    @NotNull(message = "Alcohol consumption is required!") 
    private Boolean alcoholConsumption;
    @NotNull(message = "Organ donor is required!")        
    private Boolean organDonor;
    @Size(max = 2000, message = "Remarks must not exceed 2000 characters!")
    private String remarks;
    @NotNull(message = "Patient id is required!")          
    private Long patientId;
    @NotBlank(message = "Administrator name is required")
    private String createdBy;
    private String updatedBy;
}