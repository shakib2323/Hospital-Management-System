package com.hms.dto;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DepartmentRequestDto 
{
    @NotBlank(message = "Department name is required!")
    private String departmentName;
    @NotBlank(message = "Department code is required!")
    private String departmentCode;
    @NotBlank(message = "Description is required!")
    private String description;
    @NotNull(message = "Floor number is required!")
    private Integer floorNumber;
    @NotBlank(message = "Contact extension is required!")
    private String contactExtension;
    @NotNull(message = "Branch id is required!")
    private Long branchId;
    @NotBlank(message = "Administrator name is required")
    private String createdBy;
    private String updatedBy;
}
