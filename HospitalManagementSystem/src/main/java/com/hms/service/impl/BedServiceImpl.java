package com.hms.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hms.dto.BedRequestDto;
import com.hms.dto.BedResponseDto;
import com.hms.dto.BedUpdateRequestDto;
import com.hms.entity.Admission;
import com.hms.entity.Bed;
import com.hms.entity.Patient;
import com.hms.enums.AdmissionStatus;
import com.hms.enums.BedStatus;
import com.hms.exception.ResourceNotFoundException;
import com.hms.mapper.BedMapper;
import com.hms.repository.AdmissionRepository;
import com.hms.repository.BedRepository;
import com.hms.repository.PatientRepository;
import com.hms.service.BedService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class BedServiceImpl implements BedService
{
    private final BedRepository bedRepo;
    private final BedMapper bedMapper;
    private final PatientRepository patientRepo;
    private final AdmissionRepository admissionRepo;
    
    
	@Override
	public BedResponseDto addBed(BedRequestDto dto) {
		Bed bed=bedMapper.toEntity(dto);
		bedRepo.save(bed);
		return bedMapper.toDto(bed);
	}

	@Override
	public BedResponseDto updateBed(Long bedId, BedUpdateRequestDto dto) {
		Bed bed=bedRepo.findById(bedId).orElseThrow(()->
		new ResourceNotFoundException("Bed not found with id: "+bedId));
		bed.setBedType(dto.getBedType());
		bed.setStatus(dto.getStatus());
		bed.setVentilatorSupported(dto.getVentilatorSupported());
		bed.setOxygenSupported(dto.getOxygenSupported());
		bed.setUpdatedBy(dto.getUpdatedBy());
		bedRepo.save(bed);
		return bedMapper.toDto(bed);
	}

	@Override
	public BedResponseDto assignBedToPatient(Long patientId, Long bedId) {
		Patient patient=patientRepo.findById(patientId).orElseThrow(()->
		new ResourceNotFoundException("Patient not found with id: "+patientId));
		Bed bed=bedRepo.findById(bedId).orElseThrow(()->
		new ResourceNotFoundException("Bed not found with id: "+bedId));
		  if (!bed.getStatus().equals(BedStatus.AVAILABLE)) {
		        throw new IllegalArgumentException("Bed is not available with id: " + bedId);
		    }
		  
		  bed.setStatus(BedStatus.OCCUPIED);
		  bedRepo.save(bed);
		  Admission admission = new Admission();
		    admission.setPatient(patient);
		    admission.setBed(bed);
		    admission.setAdmissionDate(LocalDate.now());
		    admission.setAdmissionStatus(AdmissionStatus.ACTIVE);
		    admissionRepo.save(admission);
		    return bedMapper.toDto(bed);
	}

	@Override
	public BedResponseDto releaseBed(Long bedId) {
		Bed bed=bedRepo.findById(bedId).orElseThrow(()->
		new ResourceNotFoundException("Bed not found with id: "+bedId));
		if (bed.getStatus().equals(BedStatus.AVAILABLE)) {
		    throw new IllegalArgumentException("Bed is already available with id: " + bedId);
		}
		bed.setStatus(BedStatus.AVAILABLE);
		bedRepo.save(bed);
		return bedMapper.toDto(bed);
	}

	@Override
	public List<BedResponseDto> getAvailableBeds() {
		List<Bed> bed=bedRepo.findAllAvailableBeds();
		return bedMapper.toDtoList(bed);
	}

	@Override
	public List<BedResponseDto> getOccupiedBeds() {
		List<Bed> bed=bedRepo.findAllOccupiedBeds();
		return bedMapper.toDtoList(bed);
	}

	@Override
	public BedResponseDto getBedById(Long bedId) {
		Bed bed=bedRepo.findById(bedId).orElseThrow(()->
		new ResourceNotFoundException("Bed not found with id: "+bedId));
		return bedMapper.toDto(bed);
	}

}
