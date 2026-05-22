package com.hms.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.hms.dto.HospitalRequestDto;
import com.hms.dto.HospitalResponseDto;
import com.hms.entity.Hospital;

@Component
public class HospitalMapper
{
    //Here convert requestDto to entity
    public Hospital toEntity(HospitalRequestDto dto)
    {
        Hospital hospital = new Hospital();
        hospital.setHospitalName(dto.getHospitalName());
        hospital.setHospitalCode(dto.getHospitalCode());
        hospital.setEmail(dto.getEmail());
        hospital.setPhoneNumber(dto.getPhoneNumber());
        hospital.setWebsite(dto.getWebsite());
        hospital.setRegistrationNumber(dto.getRegistrationNumber());
        hospital.setEstablishedYear(dto.getEstablishedYear());
        hospital.setDescription(dto.getDescription());
        hospital.setActive(true);
        return hospital;
    }

    // Convert Entity to ResponseDto
    public HospitalResponseDto toDto(Hospital hospital) 
    {
        HospitalResponseDto dto = new HospitalResponseDto();
        dto.setHospitalId(hospital.getHospitalId());
        dto.setHospitalName(hospital.getHospitalName());
        dto.setHospitalCode(hospital.getHospitalCode());
        dto.setEmail(hospital.getEmail());
        dto.setPhoneNumber(hospital.getPhoneNumber());
        dto.setWebsite(hospital.getWebsite());
        dto.setRegistrationNumber(hospital.getRegistrationNumber());
        dto.setEstablishedYear(hospital.getEstablishedYear());
        dto.setDescription(hospital.getDescription());
        dto.setActive(hospital.getActive());
        dto.setCreatedAt(hospital.getCreatedAt());
        dto.setUpdatedAt(hospital.getUpdatedAt());
        dto.setCreatedBy(hospital.getCreatedBy());
        dto.setUpdatedBy(hospital.getUpdatedBy());
        return dto;
    }
    // Convert List of Entities to List of ResponseDtos
    public List<HospitalResponseDto> toDtoList(List<Hospital> hospitals)
    {
        return hospitals.stream().map(this::toDto).collect(Collectors.toList());
    }
}