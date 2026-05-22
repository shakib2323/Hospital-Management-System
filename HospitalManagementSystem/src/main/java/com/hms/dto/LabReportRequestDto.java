package com.hms.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LabReportRequestDto 
{
    @NotNull(message = "Report date is required!")
    private LocalDate reportDate;
    @NotBlank(message = "Result summary is required!")
    private String resultSummary;
    @NotBlank(message = "Observations are required!")
    private String observations;
    @NotBlank(message = "Recommendation is required!")
    private String recommendation;
    @NotBlank(message = "Technician name is required!")
    private String technicianName;
    @NotBlank(message = "Approved by is required!")
    private String approvedBy;
    @NotNull(message = "Lab test is required!")
    private Long labTestId;
    @NotBlank(message = "Administrator name is required")
    private String createdBy;
    private String updatedBy;
}