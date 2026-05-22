package com.hms.dto;

import java.time.LocalDate;

import com.hms.enums.AdmissionStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AdmissionRequestDto 
{
    @NotNull(message = "Admission date is required")
    private LocalDate admissionDate;
    // Optional at admission time
    private LocalDate dischargeDate;
    @NotBlank(message = "Admission reason is required")
    private String admissionReason;
    @NotNull(message = "Admission status is required")
    private AdmissionStatus admissionStatus;
    @NotBlank(message = "Guardian name is required")
    private String guardianName;
    @NotBlank(message = "Guardian contact is required")
    private String guardianContact;
    @NotNull(message = "Patient is required")
    private Long patientId;
    @NotNull(message = "Doctor is required")
    private Long doctorId;
    @NotNull(message = "Bed is required")
    private Long bedId;
    @NotBlank(message = "Administrator name is required.")
    private String createdBy;
    private String updatedBy;
}