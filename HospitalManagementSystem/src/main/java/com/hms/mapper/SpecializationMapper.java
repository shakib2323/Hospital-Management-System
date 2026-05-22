package com.hms.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.hms.dto.SpecializationRequestDto;
import com.hms.dto.SpecializationResponseDto;
import com.hms.entity.Specialization;

import lombok.RequiredArgsConstructor;

@Component
public class SpecializationMapper
{
  public Specialization toEntity(SpecializationRequestDto dto)
  {
	  Specialization specialization=new Specialization();
	  specialization.setSpecializationName(dto.getSpecializationName());
	  specialization.setDescription(dto.getDescription());
	  specialization.setCreatedBy(dto.getCreatedBy());
	  specialization.setUpdatedBy(dto.getUpdatedBy());
	  return specialization;
  }
  public SpecializationResponseDto toDto(Specialization specialization)
  {
	  SpecializationResponseDto dto=new SpecializationResponseDto();
	  dto.setSpecializationName(specialization.getSpecializationName());
	  dto.setDescription(specialization.getDescription());
	  dto.setCreatedBy(specialization.getCreatedBy());
	  dto.setUpdatedBy(specialization.getUpdatedBy());
	return dto;
	  
  }
  public List<SpecializationResponseDto> toDtoList(List<Specialization> specialization)
  {
	  return specialization.stream().map(this::toDto).collect(Collectors.toList());
  }
}
