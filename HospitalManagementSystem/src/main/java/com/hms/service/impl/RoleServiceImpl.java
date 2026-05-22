package com.hms.service.impl;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.dto.RoleRequestDto;
import com.hms.dto.RoleResponseDto;
import com.hms.entity.Role;
import com.hms.exception.DuplicateResourceException;
import com.hms.exception.ResourceNotFoundException;
import com.hms.mapper.RoleMapper;
import com.hms.repository.RoleRepository;
import com.hms.service.RoleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService 
  {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public RoleResponseDto createRole(RoleRequestDto dto)
    {
    	 Boolean exist=roleRepository.existsByRoleName(dto.getRoleName());
    	 if(exist)
    	 {
    		 throw new DuplicateResourceException("Role Already exists with name: "+dto.getRoleName());
    	 }
    	 Role role=roleMapper.mapToEntity(dto);
    	 roleRepository.save(role);
    	 return roleMapper.toDto(role);
    }

    @Override
    public List<RoleResponseDto> getAllRoles() {

        List<Role> roleList = roleRepository.findAll();
        return roleMapper.toDtoList(roleList);
    }

    @Override
    public RoleResponseDto getRoleById(Long roleId) {

        Role role = roleRepository.findById(roleId).orElseThrow(()->
        new ResourceNotFoundException("There is no role preset on this id: "+roleId));
        return roleMapper.toDto(role);
}

	@Override
	public void deleteRole(Long roleId) {
		Role role=roleRepository.findById(roleId).orElseThrow(()->
        new ResourceNotFoundException("There is no role preset on this id: "+roleId));
		roleRepository.delete(role);
		
	}
}