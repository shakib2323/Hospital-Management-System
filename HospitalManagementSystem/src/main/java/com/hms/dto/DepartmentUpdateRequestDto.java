package com.hms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DepartmentUpdateRequestDto 
{
	    private String departmentName;
	    private String description;
	    private Integer floorNumber;
	    private String contactExtension;	 	  
	    private String departmentCode;
	    private Long branchId;
	    @NotBlank(message = "Administrator name is required")
	    private String updatedBy;
}
