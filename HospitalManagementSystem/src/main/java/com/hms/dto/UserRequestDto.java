package com.hms.dto;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequestDto
{
    @NotBlank(message = "Username is required!")
    private String username;
    @Email(message = "Invalid email format!")
    @NotBlank(message = "Email is required!")
    private String email;
    @NotBlank(message = "Password is required!")
    @Size(min = 8, message = "Password must be at least 8 characters!")
    private String password;
    private Boolean accountLocked = false;   
    private Boolean accountEnabled = true;   
    @NotEmpty(message = "At least one role is required!")
    private Set<Long> roleIds;
}