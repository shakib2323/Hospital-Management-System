package com.hms.dto;

import com.hms.enums.LabTestStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class LabTestRequestDto 
{
    @NotBlank(message = "Test name is required!")
    private String testName;
    @NotBlank(message = "Test code is required!")
    private String testCode;
    @NotBlank(message = "Description is required!")
    private String description;
    @NotNull(message = "Test cost is required!")
    @Positive(message = "Test cost must be a positive value!")
    private Double testCost;
    @NotBlank(message = "Normal range is required!")
    private String normalRange;
    @NotNull(message = "Status is required!")
    private LabTestStatus status;
    @NotBlank(message = "Sample type is required!")
    private String sampleType;
    @NotNull(message = "Patient is required!")
    private Long patient;
    @NotNull(message = "Doctor is required!")
    private Long doctor;
    @NotBlank(message = "Administrator name is required")
    private String createdBy;
    private String updatedBy;
}