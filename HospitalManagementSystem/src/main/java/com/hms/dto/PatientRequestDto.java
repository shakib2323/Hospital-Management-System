package com.hms.dto;

import java.time.LocalDate;

import com.hms.enums.BloodGroup;
import com.hms.enums.Gender;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class PatientRequestDto
{
    @NotBlank(message = "Patient code is required")
    private String patientCode;
    @NotBlank(message = "First name is required")
    private String firstName;
    @NotBlank(message = "Last name is required")
    private String lastName;
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;
    @NotNull(message = "Gender is required")       
    private Gender gender;
    @NotNull(message = "DOB is required")
    private LocalDate dateOfBirth;
    @NotNull(message = "Blood group is required")  
    private BloodGroup bloodGroup;
    @NotBlank(message = "Marital status is required")
    private String maritalStatus;
    @NotBlank(message = "Occupation is required")
    private String occupation;
    @NotNull(message = "Height is required")
    private Double height;
    @NotNull(message = "Weight is required")
    private Double weight;
    @NotBlank(message = "Allergies is required")
    private String allergies;
    @NotBlank(message = "Emergency contact name is required")
    private String emergencyContactName;
    @NotBlank(message = "Emergency contact number is required")
    private String emergencyContactNumber;
    @NotBlank(message = "Insurance provider is required")
    private String insuranceProvider;
    @NotBlank(message = "Insurance policy number is required")
    private String insurancePolicyNumber;
    @NotBlank(message = "Address is required")
    private String address;
    @NotBlank(message = "City is required")
    private String city;
    @NotBlank(message = "State is required")
    private String state;
    @NotBlank(message = "Pincode is required")
    private String pincode;
    @NotNull(message = "UserId is required")
    private Long userId;
    @NotBlank(message = "Administrator name is required")
    private String createdBy;
    private String updatedBy;
}
