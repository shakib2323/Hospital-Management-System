package com.hms.dto;

import com.hms.enums.BedStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BedUpdateRequestDto
{
    @NotBlank(message = "Bed type is required!")
    private String bedType;
    @NotNull(message = "Bed status is required!")
    private BedStatus status;
    @NotNull(message = "Ventilator supported is required!")
    private Boolean ventilatorSupported;
    @NotNull(message = "Oxygen supported is required!")
    private Boolean oxygenSupported;
    @NotBlank(message = "Administrator name is required.")
    private String updatedBy;
}