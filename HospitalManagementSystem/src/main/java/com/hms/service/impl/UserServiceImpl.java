package com.hms.service.impl;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hms.dto.ChangePasswordRequestDto;
import com.hms.dto.UserRequestDto;
import com.hms.dto.UserResponseDto;
import com.hms.entity.Role;
import com.hms.entity.User;
import com.hms.enums.RoleType;
import com.hms.exception.ResourceNotFoundException;
import com.hms.mapper.UserMapper;
import com.hms.repository.RoleRepository;
import com.hms.repository.UserRepository;
import com.hms.service.UserService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService
{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

   
	@Override
	public UserResponseDto registerUser(UserRequestDto dto) {
		User user=userMapper.toEntity(dto);
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		
		userRepository.save(user);
		return userMapper.toDto(user);
	}

	@Override
	public UserResponseDto getUserById(Long userId) {
		User user=userRepository.findById(userId).orElseThrow(()->
		new ResourceNotFoundException("No user associated with this id: "+userId));
		return userMapper.toDto(user);
	}

	@Override
	public List<UserResponseDto> getAllUsers() {
		 List<User> users=userRepository.findAll();
		return userMapper.toDtoList(users);
	}

//	@Override
//	public void assignRole(Long userId, Long roleId) {
//		User user=userRepository.findById(userId).orElseThrow(()->
//		new ResourceNotFoundException("User not found with this id: "+userId));
//		Role role=roleRepository.findById(roleId).orElseThrow(()->
//		new ResourceNotFoundException("User not found with this id: "+userId));
//		user.getRoles().add(role);
//		 userRepository.save(user);
//		
//	}
	@Override
	public void assignRole(Long userId, Long roleId, Authentication authentication) {

	    User user = userRepository.findById(userId)
	            .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

	    Role role = roleRepository.findById(roleId)
	            .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + roleId));

	    //Non admin from assigning admin role
	    if (role.getRoleName() == RoleType.ADMIN) {
	        boolean isAdmin = authentication.getAuthorities().stream()
	                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
	        if (!isAdmin) {
	            throw new ResourceNotFoundException("Only admin can assign the admin role");
	        }
	    }

	    //If role already assigned
	    boolean alreadyAssigned = user.getRoles().stream()
	            .anyMatch(r -> r.getRoleId().equals(roleId));
	    if (alreadyAssigned) {
	        throw new ResourceNotFoundException("Role already assigned to this user");
	    }

	    user.getRoles().add(role);
	    userRepository.save(user);
	}

	@Override
	public void lockUserAccount(Long userId){
		User user=userRepository.findById(userId).orElseThrow(()->
		new ResourceNotFoundException("User not found with this id: "+userId));
		user.setAccountLocked(true);
		userRepository.save(user);
		
	}

	@Override
	public void unlockUserAccount(Long userId){
		User user=userRepository.findById(userId).orElseThrow(()->
		new ResourceNotFoundException("User not found with this id: "+userId));
		user.setAccountEnabled(true);
		userRepository.save(user);
		
	}

	@Override
	public void changePassword(Long userId, ChangePasswordRequestDto dto) 
	{

	    User user = userRepository.findById(userId).orElseThrow(() ->
	            new ResourceNotFoundException("No user registered with this id: " + userId));

	    if (!passwordEncoder.matches(dto.getOldPassword(), user.getPassword())) 
	    {
	        throw new IllegalArgumentException("Old password does not match");
	    }

	    user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
	    userRepository.save(user);
	}

}
