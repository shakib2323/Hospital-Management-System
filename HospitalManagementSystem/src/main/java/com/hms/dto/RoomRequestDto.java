package com.hms.dto;

import com.hms.enums.RoomType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class RoomRequestDto
{
    @NotBlank(message = "Room number is required!")
    private String roomNumber;
    @NotNull(message = "Room type is required!")
    private RoomType roomType;
    @NotNull(message = "Floor number is required!")
    private Integer floorNumber;
    @NotNull(message = "Capacity is required!")
    @Positive(message = "Capacity must be positive!")
    private Integer capacity;
    @NotNull(message = "Availability status is required!")
    private Boolean availabilityStatus;
    @NotNull(message = "Daily charge is required!")
    @Positive(message = "Daily charge must be positive!")
    private Double dailyCharge;
    @NotNull(message = "Branch id is required!")
    private Long branchId;
    @NotBlank(message = "Administrator name is required")
    private String createdBy;
    private String updatedBy;
}