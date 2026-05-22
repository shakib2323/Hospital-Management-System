package com.hms.dto;

import java.time.LocalDateTime;

import com.hms.entity.Hospital;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BranchUpdateRequestDto
{
    private String branchName;
    private String city;
    private String state;
    private String country;
    private String pincode;
    private String address;
    private String branchCode;
    private String contactNumber;
    private String emergencyContact;
    private Double latitude;
    private Double longitude;
    private Long hospitalId;
    @NotBlank(message = "Administrator name is required.")
    private String updatedBy;
}
