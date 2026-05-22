package com.hms.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.hms.enums.LabTestStatus;
import lombok.Data;

@Data
public class LabReportResponseDto 
{
    private Long reportId;
    private LocalDate reportDate;
    private String resultSummary;
    private String observations;
    private String recommendation;
    private String technicianName;
    private String approvedBy;
    private Long labTestId;
    private String testName;
    private String testCode;
    private String sampleType;
    private LabTestStatus labTestStatus;
    private Long patientId;
    private String patientName;
    private Long doctorId;
    private String doctorName;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}