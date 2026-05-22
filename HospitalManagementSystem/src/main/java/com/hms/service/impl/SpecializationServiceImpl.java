package com.hms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hms.dto.SpecializationRequestDto;
import com.hms.dto.SpecializationResponseDto;
import com.hms.entity.Doctor;
import com.hms.entity.Specialization;
import com.hms.exception.ResourceNotFoundException;
import com.hms.mapper.SpecializationMapper;
import com.hms.repository.DoctorRepository;
import com.hms.repository.SpecializationRepository;
import com.hms.service.SpecializationService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class SpecializationServiceImpl implements SpecializationService
{
    private final SpecializationRepository specializationRepo;
    private final SpecializationMapper specializationMapper;
    private final DoctorRepository doctorRepo;
	@Override
	public SpecializationResponseDto addSpecialization(SpecializationRequestDto dto) {
		Specialization specialization=specializationMapper.toEntity(dto);
		specializationRepo.save(specialization);
		return specializationMapper.toDto(specialization);
	}

	@Override
	public List<SpecializationResponseDto> getAllSpecializations() {
		List<Specialization> specialization=specializationRepo.findAll();
		if(specialization.isEmpty())
		{
			new ResourceNotFoundException("There is no specializationa");
		}
		  return specializationMapper.toDtoList(specialization);
	}

	@Override
	public void assignDoctorToSpecialization(Long doctorId, Long specializationId) {
		Doctor doctor=doctorRepo.findById(doctorId).orElseThrow(()->
		new ResourceNotFoundException("Doctor not found with id: "+doctorId));
		Specialization specialization=specializationRepo.findById(specializationId).orElseThrow(()->
		new ResourceNotFoundException("Specialization not found with id: "+specializationId));
		doctor.getSpecializations().add(specialization);
		doctorRepo.save(doctor);
		
	}

	@Override
	public void removeSpecialization(Long specializationId)
	{
		Specialization specialization=specializationRepo.findById(specializationId).orElseThrow(()->
		new ResourceNotFoundException("No specialization on this id: "+specializationId));
	    specializationRepo.delete(specialization);
		
	}

}
