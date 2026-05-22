package com.hms.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.hms.dto.AmbulanceRequestDto;
import com.hms.dto.AmbulanceResponseDto;
import com.hms.entity.Ambulance;
import com.hms.entity.Branch;
import com.hms.exception.ResourceNotFoundException;
import com.hms.repository.BranchRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AmbulanceMapper
{
    private final BranchRepository branchRepo;
    public Ambulance toEntity(AmbulanceRequestDto dto)
    {
        Ambulance ambulance = new Ambulance();
        ambulance.setVehicleNumber(dto.getVehicleNumber());
        ambulance.setDriverName(dto.getDriverName());
        ambulance.setDriverPhone(dto.getDriverPhone());
        ambulance.setAvailabilityStatus(dto.getAvailabilityStatus());
        ambulance.setCurrentLocation(dto.getCurrentLocation());
        ambulance.setVehicleType(dto.getVehicleType());
        Branch branch = branchRepo.findById(dto.getBranchId())
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found with id: " + dto.getBranchId()));
        ambulance.setBranch(branch);
        ambulance.setCreatedBy(dto.getCreatedBy());
        ambulance.setUpdatedBy(dto.getUpdatedBy());
        return ambulance;
    }

    public AmbulanceResponseDto toDto(Ambulance ambulance) 
    {
        AmbulanceResponseDto dto = new AmbulanceResponseDto();
        dto.setAmbulanceId(ambulance.getAmbulanceId());
        dto.setVehicleNumber(ambulance.getVehicleNumber());
        dto.setDriverName(ambulance.getDriverName());
        dto.setDriverPhone(ambulance.getDriverPhone());
        dto.setAvailabilityStatus(ambulance.getAvailabilityStatus());
        dto.setCurrentLocation(ambulance.getCurrentLocation());
        dto.setVehicleType(ambulance.getVehicleType());
        if (ambulance.getBranch() != null) {
            dto.setBranchId(ambulance.getBranch().getBranchId());
            dto.setBranchName(ambulance.getBranch().getBranchName());
        }
        dto.setCreatedBy(ambulance.getCreatedBy());
        dto.setUpdatedBy(ambulance.getUpdatedBy());
        dto.setCreatedAt(ambulance.getCreatedAt());
        dto.setUpdatedAt(ambulance.getUpdatedAt());
        return dto;
    }

    public List<AmbulanceResponseDto> toDtoList(List<Ambulance> ambulances) {
        return ambulances.stream().map(this::toDto).collect(Collectors.toList());
    }
}