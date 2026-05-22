package com.hms.dto;

import com.hms.enums.AmbulanceStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AmbulanceRequestDto 
{
    @NotBlank(message = "Vehicle number is required!")
    private String vehicleNumber;
    @NotBlank(message = "Driver name is required!")
    private String driverName;
    @NotBlank(message = "Driver phone is required!")
    private String driverPhone;
    @NotNull(message = "Availability status is required!")
    private AmbulanceStatus availabilityStatus;
    @NotBlank(message = "Current location is required!")
    private String currentLocation;
    @NotBlank(message = "Vehicle type is required!")
    private String vehicleType;
    @NotNull(message = "Branch id is required!")
    private Long branchId;
    @NotBlank(message = "Administrator name is required.")
    private String createdBy;
    private String updatedBy;
}