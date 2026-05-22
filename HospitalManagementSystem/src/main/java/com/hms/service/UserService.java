package com.hms.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.hms.dto.ChangePasswordRequestDto;
import com.hms.dto.UserRequestDto;
import com.hms.dto.UserResponseDto;

public interface UserService
{
	public UserResponseDto registerUser(UserRequestDto dto);
	public UserResponseDto getUserById(Long userId);
	public List<UserResponseDto> getAllUsers();
//	public void assignRole(Long userId,Long roleId);
	public void assignRole(Long userId, Long roleId, Authentication authentication);
	public void lockUserAccount(Long userId);
	public void unlockUserAccount(Long userId);
	public void changePassword(Long userId,ChangePasswordRequestDto dto);
}
