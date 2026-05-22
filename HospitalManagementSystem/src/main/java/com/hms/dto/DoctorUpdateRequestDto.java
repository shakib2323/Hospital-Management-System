package com.hms.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.hms.enums.DoctorAvailabilityStatus;
import com.hms.enums.Gender;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class DoctorUpdateRequestDto
{
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Gender gender;
    private LocalDate dateOfBirth;
    private String qualification;
    private Integer experienceYears;
    private Double consultationFee;
    private String licenseNumber;
    private String specialNote;
    private LocalTime availabilityStartTime;
    private LocalTime availabilityEndTime;
    private DoctorAvailabilityStatus status;
    @NotBlank(message = "Administrator name is required")
    private String updatedBy;

}
