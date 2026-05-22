package com.hms.dto;

import java.time.LocalDateTime;
import com.hms.enums.BedStatus;
import lombok.Data;

@Data
public class BedResponseDto 
{
    private Long bedId;
    private String bedNumber;
    private String bedType;
    private BedStatus status;
    private Boolean ventilatorSupported;
    private Boolean oxygenSupported;
    private Long roomId;
    private String roomNumber;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}