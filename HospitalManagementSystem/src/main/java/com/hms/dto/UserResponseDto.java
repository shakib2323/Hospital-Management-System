package com.hms.dto;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.Data;

@Data
public class UserResponseDto
{
	    private Long userId; 
	    private String username;
	    private String email;	    
	    private Boolean accountLocked = false;      
	    private Boolean accountEnabled = true; 
	    private LocalDateTime lastLogin;
	    private LocalDateTime createdAt;	    
	    private LocalDateTime updatedAt;
	    private String createdBy;
	    private String updatedBy;
	    private String password;
	    private Set<String> roles;
}
