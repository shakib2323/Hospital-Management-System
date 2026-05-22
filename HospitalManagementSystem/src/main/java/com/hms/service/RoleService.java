package com.hms.service;

import java.util.List;

import com.hms.dto.RoleRequestDto;
import com.hms.dto.RoleResponseDto;

public interface RoleService 
{
	public RoleResponseDto createRole(RoleRequestDto dto);
	public List<RoleResponseDto> getAllRoles();
	public RoleResponseDto getRoleById(Long roleId);
	public void deleteRole(Long roleId);
	
}