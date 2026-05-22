package com.hms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hms.dto.BedResponseDto;
import com.hms.dto.RoomRequestDto;
import com.hms.dto.RoomResponseDto;
import com.hms.dto.RoomUpdateRequestDto;
import com.hms.entity.Bed;
import com.hms.entity.Room;
import com.hms.exception.ResourceNotFoundException;
import com.hms.mapper.BedMapper;
import com.hms.mapper.RoomMapper;
import com.hms.repository.BedRepository;
import com.hms.repository.RoomRepository;
import com.hms.service.RoomService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService
{
   private final RoomRepository roomRepo;
   private final RoomMapper roomMapper;
   private final BedRepository bedRepo;
   private final BedMapper bedMapper;
	@Override
	public RoomResponseDto addRoom(RoomRequestDto dto) {
	  Room room=roomMapper.toEntity(dto);
	  roomRepo.save(room);
		return roomMapper.toDto(room);
	}

	@Override
	public RoomResponseDto updateRoom(Long roomId, RoomUpdateRequestDto  dto) {
		Room room=roomRepo.findById(roomId).orElseThrow(()->
		new ResourceNotFoundException("Room not found for this id: "+roomId));
		room.setRoomType(dto.getRoomType());
		room.setFloorNumber(dto.getFloorNumber());
		room.setCapacity(dto.getCapacity());
		room.setAvailabilityStatus(dto.getAvailabilityStatus());
		room.setDailyCharge(dto.getDailyCharge());
		room.setUpdatedBy(dto.getUpdatedBy());
		roomRepo.save(room);
		return roomMapper.toDto(room);
	}

	@Override
	public RoomResponseDto getRoomById(Long roomId) {
		Room room=roomRepo.findById(roomId).orElseThrow(()->
		new ResourceNotFoundException("Room not found for this id: "+roomId));
		return roomMapper.toDto(room);
	}

	@Override
	public List<RoomResponseDto> getAllRooms() {
		List<Room> room=roomRepo.findAll();
		return roomMapper.toDtoList(room);
	}

	@Override
	public List<BedResponseDto> getRoomBeds(Long roomId) {
	    Room room = roomRepo.findById(roomId)
	            .orElseThrow(() -> new ResourceNotFoundException(
	                    "Room not found with id: " + roomId));

	    List<Bed> beds = bedRepo.findByRoom(room);
	    return bedMapper.toDtoList(beds);
	}

	@Override
	public List<RoomResponseDto> getAvailableRooms() {
	    List<Room> rooms = roomRepo.findAvailableRooms();
	    return roomMapper.toDtoList(rooms);
	}

	@Override
	public void deleteRoom(Long roomId) {
		Room room=roomRepo.findById(roomId).orElseThrow(()->
		new ResourceNotFoundException("Room not found for this id: "+roomId));
		roomRepo.delete(room);
		
	}

	

}
