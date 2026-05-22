package com.hms.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class HospitalUpdateRequestDto 
{
    private String hospitalName;
    private String email;
    private String phoneNumber;
    private String website;
    private String description;
    private String hospitalCode;
    private String registrationNumber;
    private Integer establishedYear;
    @NotBlank(message = "Administrator name is required")
    private String updatedBy;
}
