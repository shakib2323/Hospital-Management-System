package com.hms.dto;
import com.hms.enums.RoleType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleRequestDto {
    @NotNull(message = "Role name is required")
    private RoleType roleName;
    @Size(max = 300, message = "Description must not exceed 300 characters!")
    private String description;
    @NotBlank(message = "Administrator name is required")
    public String createdBy;
    public String updatedBy;
}