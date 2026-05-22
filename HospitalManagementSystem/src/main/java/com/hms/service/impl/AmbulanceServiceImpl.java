package com.hms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hms.audit.AuditActionType;
import com.hms.audit.AuditLogService;
import com.hms.dto.AmbulanceRequestDto;
import com.hms.dto.AmbulanceResponseDto;
import com.hms.entity.Ambulance;
import com.hms.entity.Patient;
import com.hms.enums.AmbulanceStatus;
import com.hms.exception.ResourceNotFoundException;
import com.hms.mapper.AmbulanceMapper;
import com.hms.repository.AmbulanceRepository;
import com.hms.repository.PatientRepository;
import com.hms.service.AmbulanceService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AmbulanceServiceImpl implements AmbulanceService {

    private final AuditLogService auditLogService;

    private final AmbulanceRepository ambulanceRepo;
    private final AmbulanceMapper ambulanceMapper;
    private final PatientRepository patientRepo;
    

    @Override
    public AmbulanceResponseDto registerAmbulance(AmbulanceRequestDto dto) {
        Ambulance ambulance = ambulanceMapper.toEntity(dto);
        ambulanceRepo.save(ambulance);
        auditLogService.log(
        		dto.getCreatedBy(),
        		AuditActionType.CREATE.name(),
        		"Ambulance",
        		"Register Ambulance: "+dto.getDriverName()
        		);
        return ambulanceMapper.toDto(ambulance);
    }

    @Override
    public AmbulanceResponseDto updateAmbulance(Long ambulanceId, AmbulanceRequestDto dto) {
        Ambulance ambulance = ambulanceRepo.findById(ambulanceId)
                .orElseThrow(() -> new ResourceNotFoundException("Ambulance not found with id: " + ambulanceId));

        if (dto.getVehicleNumber() != null &&
                !dto.getVehicleNumber().equals(ambulance.getVehicleNumber())) {
            throw new ResourceNotFoundException("Vehicle number is not updatable!");
        }
        if (dto.getBranchId() != null &&
                !dto.getBranchId().equals(ambulance.getBranch().getBranchId())) {
            throw new ResourceNotFoundException("Branch id is not updatable!");
        }
        ambulance.setDriverName(dto.getDriverName());
        ambulance.setDriverPhone(dto.getDriverPhone());
        ambulance.setAvailabilityStatus(dto.getAvailabilityStatus());
        ambulance.setCurrentLocation(dto.getCurrentLocation());
        ambulance.setVehicleType(dto.getVehicleType());
        ambulance.setUpdatedBy(dto.getUpdatedBy());
        ambulanceRepo.save(ambulance);
        auditLogService.log(dto.getUpdatedBy(), AuditActionType.UPDATE.name(), "Ambulance", "Update Ambulance with id: "+ambulanceId);
        return ambulanceMapper.toDto(ambulance);
    }

    @Override
    public AmbulanceResponseDto assignAmbulance(Long patientId, Long ambulanceId) {
        Patient patient = patientRepo.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + patientId));
        Ambulance ambulance = ambulanceRepo.findById(ambulanceId)
                .orElseThrow(() -> new ResourceNotFoundException("Ambulance not found with id: " + ambulanceId));
        if (!ambulance.getAvailabilityStatus().equals(AmbulanceStatus.AVAILABLE)) 
        {
            throw new IllegalStateException("Ambulance is not available with id: " + ambulanceId);
        }
        ambulance.setAvailabilityStatus(AmbulanceStatus.ON_DUTY);
        ambulanceRepo.save(ambulance);
        auditLogService.log("system", AuditActionType.ASSIGN.name(), "ambulance", "Ambulance assign with patientid: "+patientId);
        return ambulanceMapper.toDto(ambulance);
    }

    @Override
    public List<AmbulanceResponseDto> getAvailableAmbulances() {
        List<Ambulance> ambulances = ambulanceRepo.findByAvailabilityStatus(AmbulanceStatus.AVAILABLE);
        return ambulanceMapper.toDtoList(ambulances);
    }

    @Override
    public AmbulanceResponseDto trackAmbulance(Long ambulanceId) {
        Ambulance ambulance = ambulanceRepo.findById(ambulanceId)
                .orElseThrow(() -> new ResourceNotFoundException("Ambulance not found with id: " + ambulanceId));
        return ambulanceMapper.toDto(ambulance);
    }

    @Override
    public void removeAmbulance(Long ambulanceId) {
        Ambulance ambulance = ambulanceRepo.findById(ambulanceId)
                .orElseThrow(() -> new ResourceNotFoundException("Ambulance not found with id: " + ambulanceId));
        if (ambulance.getAvailabilityStatus().equals(AmbulanceStatus.ON_DUTY)) {
            throw new IllegalStateException("Cannot remove ambulance that is currently ON_DUTY!");
        }
        ambulanceRepo.delete(ambulance);
        auditLogService.log("system", AuditActionType.DELETE.name(), "Ambulance", "Ambulance delete with id: "+ambulanceId);
    }
}