package com.hms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.dto.BedResponseDto;
import com.hms.dto.RoomRequestDto;
import com.hms.dto.RoomResponseDto;
import com.hms.dto.RoomUpdateRequestDto;
import com.hms.response.ApiResponse;
import com.hms.service.RoomService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
public class RoomController {
  private final RoomService roomService;
  @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
  @PostMapping("/")
  public ResponseEntity<ApiResponse<RoomResponseDto>> addRooms(@Valid @RequestBody RoomRequestDto dto)
  {
	  RoomResponseDto room=roomService.addRoom(dto);
	  ApiResponse<RoomResponseDto>response=ApiResponse.<RoomResponseDto>builder()
			  .success(true)
			  .message("Room added successfully")
			  .statusCode(201)
			  .data(room)
			  .build();
	  return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
  @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
  @PatchMapping("/{roomId}/update")
  public ResponseEntity<ApiResponse<RoomResponseDto>> updateRooms(@PathVariable Long roomId,@Valid @RequestBody RoomUpdateRequestDto dto)
  {
	  RoomResponseDto room=roomService.updateRoom(roomId, dto);
	  ApiResponse<RoomResponseDto>response=ApiResponse.<RoomResponseDto>builder()
			  .success(true)
			  .message("Room updated successfully")
			  .statusCode(200)
			  .data(room)
			  .build();
	  return ResponseEntity.ok(response);
  }
  @GetMapping("/{roomId}")
  public ResponseEntity<ApiResponse<RoomResponseDto>> getRoomById(@PathVariable Long roomId)
  {
	  RoomResponseDto room=roomService.getRoomById(roomId);
	  ApiResponse<RoomResponseDto>response=ApiResponse.<RoomResponseDto>builder()
			  .success(true)
			  .message("Room fetched successfully")
			  .statusCode(200)
			  .data(room)
			  .build();
	  return ResponseEntity.ok(response);
  }
  @GetMapping("/")
  public ResponseEntity<ApiResponse<List<RoomResponseDto>>> getAllRoom()
  {
	  List<RoomResponseDto> room=roomService.getAllRooms();
	  ApiResponse<List<RoomResponseDto>>response=ApiResponse.<List<RoomResponseDto>>builder()
			  .success(true)
			  .message("All Rooms fetched successfully")
			  .statusCode(200)
			  .data(room)
			  .build();
	  return ResponseEntity.ok(response);
  }
  @GetMapping("/{roomId}/bed")
  public ResponseEntity<ApiResponse<List<BedResponseDto>>> getRoomBeds(@PathVariable Long roomId)
  {
	  List<BedResponseDto> bed=roomService.getRoomBeds(roomId);
	  ApiResponse<List<BedResponseDto>>response=ApiResponse.<List<BedResponseDto>>builder()
			  .success(true)
			  .message("Room beds fetched successfully")
			  .statusCode(200)
			  .data(bed)
			  .build();
	  return ResponseEntity.ok(response);
  }
  @GetMapping("/available")
  public ResponseEntity<ApiResponse<List<RoomResponseDto>>> getAvailableRoom()
  {
	  List<RoomResponseDto> room=roomService.getAvailableRooms();
	  ApiResponse<List<RoomResponseDto>>response=ApiResponse.<List<RoomResponseDto>>builder()
			  .success(true)
			  .message("Available Room fetched successfully")
			  .statusCode(200)
			  .data(room)
			  .build();
	  return ResponseEntity.ok(response);
  }
  @GetMapping("/{roomId}/delete")
  public ResponseEntity<ApiResponse<String>> deleteRooms(@PathVariable Long roomId)
  {
	 roomService.deleteRoom(roomId);
	  ApiResponse<String>response=ApiResponse.<String>builder()
			  .success(true)
			  .message("Room deleted successfully")
			  .statusCode(200)
			  .data(null)
			  .build();
	  return ResponseEntity.ok(response);
  }
}
