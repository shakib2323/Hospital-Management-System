package com.hms.mapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.hms.dto.UserRequestDto;
import com.hms.dto.UserResponseDto;
import com.hms.entity.Role;
import com.hms.entity.User;
import com.hms.exception.ResourceNotFoundException;
import com.hms.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserMapper
{
    private final RoleRepository roleRepo;
    public User toEntity(UserRequestDto dto)
    {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword()); 
        user.setAccountLocked(dto.getAccountLocked());  
        user.setAccountEnabled(dto.getAccountEnabled());
        Set<Role> roles = new HashSet<>();
        for (Long id : dto.getRoleIds()) {
            Role role = roleRepo.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + id));
            roles.add(role);
        }
        user.setRoles(roles);
        return user;
    }

    public UserResponseDto toDto(User user) 
    {
        UserResponseDto dto = new UserResponseDto();
        dto.setUserId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setAccountLocked(user.getAccountLocked());
        dto.setAccountEnabled(user.getAccountEnabled());
        dto.setLastLogin(user.getLastLogin());
        Set<String> roleNames = user.getRoles().stream()
                .map(role -> role.getRoleName().name())
                .collect(Collectors.toSet());
        dto.setRoles(roleNames);
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());
        dto.setCreatedBy(user.getCreatedBy());
        dto.setUpdatedBy(user.getUpdatedBy());
        return dto;
    }

    public List<UserResponseDto> toDtoList(List<User> users) 
    {
        return users.stream().map(this::toDto).collect(Collectors.toList());
    }
}
