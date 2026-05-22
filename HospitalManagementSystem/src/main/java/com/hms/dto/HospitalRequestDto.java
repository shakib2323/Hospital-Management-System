package com.hms.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class HospitalRequestDto
{
    @NotBlank(message = "Hospital name is required") 
    private String hospitalName;
    @NotBlank(message = "Hospital code is required")
    private String hospitalCode;
    @Email(message = "Invalid email format")
    @NotBlank(message = "Hospital email is required")
    private String email;
    @NotBlank(message = "Phone number is required")
    @Size(min = 10, max = 20, message = "Phone number must be between 10 to 20 characters")
    private String phoneNumber;
    private String website; 
    @NotBlank(message = "Registration number is required")
    @Size(min = 3, max = 100, message = "Registration number must be between 3 to 100 characters") 
    private String registrationNumber;
    @NotNull(message = "Established year is required") 
    private Integer establishedYear;
    @NotBlank(message = "Description is required")
    @Size(min = 20, max = 1000, message = "Description must be between 20 to 1000 characters")
    private String description;
    @NotBlank(message = "Administrator name is required")
    private String createdBy;
    private String updatedBy;
}