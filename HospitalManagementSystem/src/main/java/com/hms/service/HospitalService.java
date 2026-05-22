package com.hms.service;

import java.util.List;

import com.hms.dto.BranchResponseDto;
import com.hms.dto.HospitalRequestDto;
import com.hms.dto.HospitalResponseDto;
import com.hms.dto.HospitalUpdateRequestDto;

public interface HospitalService {

    public HospitalResponseDto registerHospital( HospitalRequestDto dto);
    public HospitalResponseDto updateHospital( Long hospitalId, HospitalUpdateRequestDto dto);
    public HospitalResponseDto getHospitalById(Long hospitalId);
    public List<HospitalResponseDto> getAllHospitals();
    public List<BranchResponseDto> getHospitalBranches(Long hospitalId);
    public void deactivateHospital(Long hospitalId);
    public void activateHospital(Long hospitalId);
}
