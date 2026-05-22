package com.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.dto.RoleRequestDto;
import com.hms.dto.RoleResponseDto;
import com.hms.response.ApiResponse;
import com.hms.service.RoleService;
@RestController
@RequestMapping("/api/v1/roles")
public class RoleController
{   @Autowired
	private RoleService roleService;
    @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
    @PostMapping
    public ResponseEntity<ApiResponse<RoleResponseDto>> createRole(@Validated @RequestBody RoleRequestDto dto) {
    RoleResponseDto responseDto =roleService.createRole(dto);
    ApiResponse<RoleResponseDto> response =ApiResponse.<RoleResponseDto>builder()
                        .success(true)
                        .message("Role created successfully")
                        .data(responseDto)
                        .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<RoleResponseDto>>> getAllRoles()
    {
        List<RoleResponseDto> roleList =roleService.getAllRoles();
        ApiResponse<List<RoleResponseDto>> response =ApiResponse.<List<RoleResponseDto>>builder()
                        .success(true)
                        .message("Roles fetched successfully")
                        .data(roleList)
                        .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<ApiResponse<RoleResponseDto>> getRoleById( @PathVariable Long roleId) 
    {
        RoleResponseDto responseDto =roleService.getRoleById(roleId);
        ApiResponse<RoleResponseDto> response =ApiResponse.<RoleResponseDto>builder()
                        .success(true)
                        .message("Role fetched successfully")
                        .data(responseDto)
                        .build();

        return ResponseEntity.ok(response);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{roleId}/delete")
    public ResponseEntity<ApiResponse<String>> deleteRole(@PathVariable Long roleId)
    {
        roleService.deleteRole(roleId);
        ApiResponse<String> response =ApiResponse.<String>builder()
                        .success(true)
                        .message("Role deleted successfully")
                        .data(null)
                        .build();

        return ResponseEntity.ok(response);
    }
}

