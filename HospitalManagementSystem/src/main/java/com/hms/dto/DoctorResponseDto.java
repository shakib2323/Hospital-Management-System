package com.hms.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.hms.enums.DoctorAvailabilityStatus;
import com.hms.enums.Gender;

import lombok.Data;

@Data
public class DoctorResponseDto
{
    private Long doctorId;      
    private String doctorCode;
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
    private Long departmentId;
    private String departmentName; 
    private Long userId;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}