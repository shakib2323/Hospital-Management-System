package com.hms.dto;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class DepartmentResponseDto 
{
	    private Long departmentId;
	    private String departmentName;	
	    private String departmentCode;
	    private String description;	   
	    private Integer floorNumber;	   
	    private String contactExtension;	  
	    private Long branchId;
	    private String branchName;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
	    private String createdBy;
	    private String updatedBy;
}
