package com.hms.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.hms.dto.BedRequestDto;
import com.hms.dto.BedResponseDto;
import com.hms.entity.Bed;
import com.hms.entity.Room;
import com.hms.exception.ResourceNotFoundException;
import com.hms.repository.RoomRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BedMapper 
{
    private final RoomRepository roomRepo;
    public Bed toEntity(BedRequestDto dto) 
    {
        Bed bed = new Bed();
        bed.setBedNumber(dto.getBedNumber());
        bed.setBedType(dto.getBedType());
        bed.setStatus(dto.getStatus());
        bed.setVentilatorSupported(dto.getVentilatorSupported());
        bed.setOxygenSupported(dto.getOxygenSupported());
        Room room = roomRepo.findById(dto.getRoomId())
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id: " + dto.getRoomId()));
        bed.setRoom(room);
        bed.setCreatedBy(dto.getCreatedBy());
        bed.setUpdatedBy(dto.getUpdatedBy());
        return bed;
    }
    public BedResponseDto toDto(Bed bed)
    {
        BedResponseDto dto = new BedResponseDto();
        dto.setBedId(bed.getBedId());
        dto.setBedNumber(bed.getBedNumber());
        dto.setBedType(bed.getBedType());
        dto.setStatus(bed.getStatus());
        dto.setVentilatorSupported(bed.getVentilatorSupported());
        dto.setOxygenSupported(bed.getOxygenSupported());
        if (bed.getRoom() != null) {
            dto.setRoomId(bed.getRoom().getRoomId());
            dto.setRoomNumber(bed.getRoom().getRoomNumber());
        }
        dto.setCreatedBy(bed.getCreatedBy());
        dto.setUpdatedBy(bed.getUpdatedBy());
        dto.setCreatedAt(bed.getCreatedAt());
        dto.setUpdatedAt(bed.getUpdatedAt());
        return dto;
    }
    public List<BedResponseDto> toDtoList(List<Bed> beds) 
    {
        return beds.stream().map(this::toDto).collect(Collectors.toList());
    }
}