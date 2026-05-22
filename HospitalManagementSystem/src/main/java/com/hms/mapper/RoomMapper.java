package com.hms.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.hms.dto.RoomRequestDto;
import com.hms.dto.RoomResponseDto;
import com.hms.entity.Branch;
import com.hms.entity.Room;
import com.hms.exception.ResourceNotFoundException;
import com.hms.repository.BranchRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RoomMapper
{
    private final BranchRepository branchRepo;
    public Room toEntity(RoomRequestDto dto)
    {
        Room room = new Room();
        room.setRoomNumber(dto.getRoomNumber());
        room.setRoomType(dto.getRoomType());
        room.setFloorNumber(dto.getFloorNumber());
        room.setCapacity(dto.getCapacity());
        room.setAvailabilityStatus(dto.getAvailabilityStatus());
        room.setDailyCharge(dto.getDailyCharge());
        Branch branch = branchRepo.findById(dto.getBranchId())
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found with id: " + dto.getBranchId()));
        room.setBranch(branch);
        room.setCreatedBy(dto.getCreatedBy());
        room.setUpdatedBy(dto.getUpdatedBy());
        return room;
    }

    public RoomResponseDto toDto(Room room)
    {
        RoomResponseDto dto = new RoomResponseDto();
        dto.setRoomId(room.getRoomId());
        dto.setRoomNumber(room.getRoomNumber());
        dto.setRoomType(room.getRoomType());
        dto.setFloorNumber(room.getFloorNumber());
        dto.setCapacity(room.getCapacity());
        dto.setAvailabilityStatus(room.getAvailabilityStatus());
        dto.setDailyCharge(room.getDailyCharge());
        if (room.getBranch() != null) {
            dto.setBranchId(room.getBranch().getBranchId());
            dto.setBranchName(room.getBranch().getBranchName());
        }
        dto.setCreatedBy(room.getCreatedBy());
        dto.setUpdatedBy(room.getUpdatedBy());
        dto.setCreatedAt(room.getCreatedAt());
        dto.setUpdatedAt(room.getUpdatedAt());
        return dto;
    }

    public List<RoomResponseDto> toDtoList(List<Room> rooms) 
    {
        return rooms.stream().map(this::toDto).collect(Collectors.toList());
    }
}