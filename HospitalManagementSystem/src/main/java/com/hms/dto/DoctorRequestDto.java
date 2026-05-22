package com.hms.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.hms.enums.DoctorAvailabilityStatus;
import com.hms.enums.Gender;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DoctorRequestDto
{
    @NotBlank(message = "Doctor code is required!")
    private String doctorCode;
    @NotBlank(message = "First name is required!")
    private String firstName;
    @NotBlank(message = "Last name is required!")
    private String lastName;
    @Email(message = "Invalid email format!")
    @NotBlank(message = "Email is required!")
    private String email;
    @NotBlank(message = "Phone number is required!")
    private String phoneNumber;
    @NotNull(message = "Gender is required!")
    private Gender gender;
    @NotNull(message = "Date of birth is required!")
    private LocalDate dateOfBirth;
    @NotBlank(message = "Qualification is required!")
    private String qualification;
    @NotNull(message = "Experience years is required!")
    private Integer experienceYears;
    @NotNull(message = "Consultation fee is required!")
    private Double consultationFee;
    @NotBlank(message = "License number is required!")
    private String licenseNumber;
    @Size(max = 1000, message = "Special note must not exceed 1000 characters!")
    private String specialNote;
    @NotNull(message = "Doctor starting available time is required!")
    private LocalTime availabilityStartTime;
    @NotNull(message = "Doctor closing time is required!")
    private LocalTime availabilityEndTime;
    private DoctorAvailabilityStatus status;
    @NotNull(message = "Department is required!")
    private Long departmentId;
    @NotNull(message = "User is required!")
    private Long userId;
    @NotBlank(message = "Administrator name is required")
    private String createdBy;
    private String updatedBy;
}