package com.hms.dto;

import java.time.LocalDateTime;
import com.hms.enums.RoomType;
import lombok.Data;

@Data
public class RoomResponseDto 
{
    private Long roomId;
    private String roomNumber;
    private RoomType roomType;
    private Integer floorNumber;
    private Integer capacity;
    private Boolean availabilityStatus;
    private Double dailyCharge;
    private Long branchId;
    private String branchName;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}