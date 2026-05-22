package com.hms.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PrescriptionUpdateRequestDto 
{
    private String diagnosis;
    private String doctorNotes;
    private LocalDate followUpDate;
    private LocalDate prescribedDate; 
    @NotBlank(message = "Administrator name is required")
    private String updatedBy;
}