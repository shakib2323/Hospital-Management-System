package com.hms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.dto.ChangePasswordRequestDto;
import com.hms.dto.UserRequestDto;
import com.hms.dto.UserResponseDto;
import com.hms.response.ApiResponse;
import com.hms.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController 
{
 private final UserService userService;
 @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
 @PostMapping
 public ResponseEntity<ApiResponse<UserResponseDto>> addUser(@Valid @RequestBody UserRequestDto dto)
 {
	 UserResponseDto user=userService.registerUser(dto);
	 ApiResponse<UserResponseDto> response=ApiResponse.<UserResponseDto>builder()
			 .success(true)
			 .message("User register successfully!")
			 .statusCode(201)
			 .data(user)
			 .build();
	 return ResponseEntity.status(HttpStatus.CREATED).body(response);
 }
 @GetMapping("/{userid}")
 public ResponseEntity<ApiResponse<UserResponseDto>> findUserById(@PathVariable Long userid)
 {
	 UserResponseDto user=userService.getUserById(userid);
	 ApiResponse<UserResponseDto> response=ApiResponse.<UserResponseDto>builder()
			 .success(true)
			 .message("User fetched successfully by id: "+userid)
			 .statusCode(200)
			 .data(user)
			 .build();
	return ResponseEntity.ok(response); 
 }
 @GetMapping
 public ResponseEntity<ApiResponse<List<UserResponseDto>>> findAllUser()
 {
	 List<UserResponseDto> users=userService.getAllUsers();

	 ApiResponse<List<UserResponseDto>> response=ApiResponse.<List<UserResponseDto>>builder()
			 .success(true)
			 .message("All user found successfully!")
			 .statusCode(200)
			 .data(users)
			 .build();
	 return ResponseEntity.ok(response);
 }
// @PostMapping("/{userId}/assign-role/{roleId}")
// public ResponseEntity<ApiResponse<String>> assignRoles(@PathVariable Long userId,@PathVariable Long roleId)
// {
//	 userService.assignRole(userId, roleId);
//	 ApiResponse<String> response=ApiResponse.<String>builder()
//			 .success(true)
//			 .message("Role assign successfully!")
//			 .statusCode(200)
//			 .data(null)
//			 .build();
//	return ResponseEntity.ok(response); 
// }
 @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
 @PostMapping("/{userId}/assign-role/{roleId}")
 public ResponseEntity<ApiResponse<String>> assignRole(@PathVariable Long userId,@PathVariable Long roleId,
         Authentication authentication) {  

     userService.assignRole(userId, roleId, authentication);
     ApiResponse<String>response=ApiResponse.<String>builder()
    		 .success(true)
    		 .message("Role assign successfully")
    		 .statusCode(200)
    		 .data(null)
    		 .build();
     return ResponseEntity.ok(response);
 }
 @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
 @PatchMapping("/{userId}/lock")
 public ResponseEntity<ApiResponse<String>> lockUserAccountById(@PathVariable Long userId)
 {
	 userService.lockUserAccount(userId);
	 ApiResponse<String> response=ApiResponse.<String>builder()
			 .success(true)
			 .message("Account locked successfully with id: "+userId)
			 .statusCode(200)
			 .data(null)
			 .build();
	return ResponseEntity.ok(response);
	 
 }
 @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
 @PatchMapping("/{userId}/unlock")
 public ResponseEntity<ApiResponse<String>> unlockUserAccountById(@PathVariable Long userId)
 {
	 userService.unlockUserAccount(userId);
	 ApiResponse<String> response=ApiResponse.<String>builder()
			 .success(true)
			 .message("Account unlocked successfully with id: "+userId)
			 .statusCode(200)
			 .data(null)
			 .build();
	return ResponseEntity.ok(response);
	 
 }
 @PostMapping("/{userId}/change-password")
 public ResponseEntity<ApiResponse<String>> changeUserPasswordById(@PathVariable Long userId,@Valid @RequestBody ChangePasswordRequestDto  dto)
 {
	 userService.changePassword(userId, dto);
	 ApiResponse<String> response=ApiResponse.<String>builder()
			 .success(true)
			 .message("Password changed successfully with id: "+userId)
			 .statusCode(200)
			 .data(null)
			 .build();
	return ResponseEntity.ok(response);
	
 }
}
