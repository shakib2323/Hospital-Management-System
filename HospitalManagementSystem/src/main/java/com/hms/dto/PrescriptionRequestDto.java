package com.hms.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PrescriptionRequestDto 
{
    @NotBlank(message = "Diagnosis is required!")
    @Size(max = 2000, message = "Diagnosis must not exceed 2000 characters!")
    private String diagnosis;
    @NotBlank(message = "Doctor notes is required!")
    @Size(max = 2000, message = "Doctor notes must not exceed 2000 characters!")
    private String doctorNotes;
    @NotNull(message = "Follow up date is required!")
    private LocalDate followUpDate;
    @NotNull(message = "Prescribed date is required!")
    private LocalDate prescribedDate;
    @NotNull(message = "Appointment id is required!")  
    private Long appointmentId;
    @NotNull(message = "Doctor id is required!")
    private Long doctorId;
    @NotNull(message = "Patient id is required!")
    private Long patientId;
    @NotBlank(message = "Administrator name is required")
    private String createdBy;
    private String updatedBy;
}