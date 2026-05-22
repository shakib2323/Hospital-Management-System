package com.hms.service;

import java.util.List;

import com.hms.dto.BedResponseDto;
import com.hms.dto.RoomRequestDto;
import com.hms.dto.RoomResponseDto;
import com.hms.dto.RoomUpdateRequestDto;

public interface RoomService 
{
	public RoomResponseDto addRoom(RoomRequestDto dto);
	public RoomResponseDto updateRoom(Long roomId,RoomUpdateRequestDto  dto);
	public RoomResponseDto getRoomById(Long roomId);
	public List<RoomResponseDto> getAllRooms();
	public List<BedResponseDto> getRoomBeds(Long roomId);
	public List<RoomResponseDto>getAvailableRooms();
	public void deleteRoom(Long roomId);
}