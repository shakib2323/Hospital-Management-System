package com.hms.dto;

import com.hms.enums.LabTestStatus;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class LabTestResponseDto
{
    private Long id;
    private String testName;
    private String testCode;
    private String description;
    private Double testCost;
    private String normalRange;
    private LabTestStatus status;
    private String sampleType;
    private Long patientId;
    private String patientName;
    private Long doctorId;
    private String doctorName;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}