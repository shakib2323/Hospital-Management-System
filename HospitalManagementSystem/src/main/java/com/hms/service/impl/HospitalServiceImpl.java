package com.hms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hms.dto.BranchResponseDto;
import com.hms.dto.HospitalRequestDto;
import com.hms.dto.HospitalResponseDto;
import com.hms.dto.HospitalUpdateRequestDto;
import com.hms.entity.Hospital;
import com.hms.exception.HospitalAlreadyActiveException;
import com.hms.exception.HospitalNotFoundException;
import com.hms.exception.InvalidOperationException;
import com.hms.exception.ResourceNotFoundException;
import com.hms.mapper.HospitalMapper;
import com.hms.repository.HospitalRepository;
import com.hms.service.HospitalService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HospitalServiceImpl implements HospitalService 
{
    private final HospitalRepository hospitalRepo;
    private final HospitalMapper hospitalMapper;

    @Override
    public HospitalResponseDto registerHospital(HospitalRequestDto dto) {
        Hospital hospital = hospitalMapper.toEntity(dto);
        Hospital save = hospitalRepo.save(hospital);
        return hospitalMapper.toDto(save);
    }

    @Override
    public HospitalResponseDto updateHospital(Long hospitalId, HospitalUpdateRequestDto dto) 
    {
        Hospital hospital = hospitalRepo.findById(hospitalId).orElseThrow(() -> new 
        		HospitalNotFoundException("Hospital not found with id: " + hospitalId));
        Map<String, String> map = new HashMap<>();
        if (dto.getHospitalCode() != null) {
            map.put("codeMsg", "Not Updatable field!");
        }
        if (dto.getEstablishedYear() != null) {
            map.put("yearMsg", "Not Updatable field!");
        }
        if (dto.getRegistrationNumber() != null) {
            map.put("regMsg", "Not Updatable field!");
        }
        if (!map.isEmpty()) {
            throw new InvalidOperationException(map);
        }

        hospital.setHospitalName(dto.getHospitalName());
        hospital.setEmail(dto.getEmail());
        hospital.setPhoneNumber(dto.getPhoneNumber());
        hospital.setWebsite(dto.getWebsite());
        hospital.setDescription(dto.getDescription());

        Hospital updatedHospital = hospitalRepo.save(hospital);
        return hospitalMapper.toDto(updatedHospital);
    }

    @Override
    public HospitalResponseDto getHospitalById(Long hospitalId)
    {
        Hospital find = hospitalRepo.findById(hospitalId)
                .orElseThrow(() -> new HospitalNotFoundException("Hospital not found with id: " + hospitalId)); 
        return hospitalMapper.toDto(find);
    }

    @Override
    public List<HospitalResponseDto> getAllHospitals()
    {
        List<Hospital> hospitals = hospitalRepo.findAll();
        return hospitalMapper.toDtoList(hospitals);
    }

    @Override
    public void deactivateHospital(Long hospitalId)
    {
        Hospital hospital = hospitalRepo.findById(hospitalId)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid hospital id: " + hospitalId));
        if (!hospital.getActive()) 
        {
            throw new HospitalAlreadyActiveException("Hospital is already deactivated"); 
        }
        hospital.setActive(false);
        hospitalRepo.save(hospital); 
    }

    @Override
    public void activateHospital(Long hospitalId) 
    {
        Hospital hospital = hospitalRepo.findById(hospitalId)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid hospital id: " + hospitalId));
        if (hospital.getActive()) 
        {
            throw new HospitalAlreadyActiveException("Hospital is already active"); 
        }
        hospital.setActive(true);
        hospitalRepo.save(hospital); 
    }

    @Override
    public List<BranchResponseDto> getHospitalBranches(Long hospitalId) {
        // TODO — implement later
        return null;
    }
}