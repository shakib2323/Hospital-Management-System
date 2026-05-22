package com.hms.mapper;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.hms.dto.RoleRequestDto;
import com.hms.dto.RoleResponseDto;
import com.hms.entity.Role;

@Component
public class RoleMapper
{

    public Role mapToEntity(RoleRequestDto dto)
    {
    	Role role=new Role();
    	role.setRoleName(dto.getRoleName());
    	role.setDescription(dto.getDescription());
    	return role;
    }
  
    public RoleResponseDto toDto(Role role) 
    {
        return RoleResponseDto.builder()
                .roleId(role.getRoleId())
                .roleName(role.getRoleName())
                .description(role.getDescription())
                .build();
    }
    public List<RoleResponseDto> toDtoList(List<Role>role)
    {
    	return role.stream().map(this::toDto).collect(Collectors.toList());
    }
}
