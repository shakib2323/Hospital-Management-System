package com.hms.dto;

import java.time.LocalDateTime;
import com.hms.enums.AmbulanceStatus;
import lombok.Data;

@Data
public class AmbulanceResponseDto 
{
    private Long ambulanceId;
    private String vehicleNumber;
    private String driverName;
    private String driverPhone;
    private AmbulanceStatus availabilityStatus;
    private String currentLocation;
    private String vehicleType;
    private Long branchId;
    private String branchName;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}