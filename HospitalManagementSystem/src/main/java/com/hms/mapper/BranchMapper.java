package com.hms.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;

import com.hms.dto.BranchRequestDto;
import com.hms.dto.BranchResponseDto;
import com.hms.entity.Branch;
import com.hms.entity.Hospital;
@Controller
public class BranchMapper 
{
    // Convert RequestDto to Entity
    public Branch toEntity(BranchRequestDto dto) 
    {
        Branch branch = new Branch();
        branch.setBranchName(dto.getBranchName());
        branch.setBranchCode(dto.getBranchCode());
        branch.setCity(dto.getCity());
        branch.setState(dto.getState());
        branch.setCountry(dto.getCountry());
        branch.setPincode(dto.getPincode());
        branch.setAddress(dto.getAddress());
        branch.setContactNumber(dto.getContactNumber());
        branch.setEmergencyContact(dto.getEmergencyContact());
        branch.setLatitude(dto.getLatitude());
        branch.setLongitude(dto.getLongitude());
        Hospital hospital = new Hospital();
        branch.setHospital(hospital);
        return branch;
    }

    // Convert Entity to ResponseDto
    public BranchResponseDto toDto(Branch branch)
    {
        BranchResponseDto dto = new BranchResponseDto();
        dto.setBranchId(branch.getBranchId());
        dto.setBranchName(branch.getBranchName());
        dto.setBranchCode(branch.getBranchCode());
        dto.setCity(branch.getCity());
        dto.setState(branch.getState());
        dto.setCountry(branch.getCountry());
        dto.setPincode(branch.getPincode());
        dto.setAddress(branch.getAddress());
        dto.setContactNumber(branch.getContactNumber());
        dto.setEmergencyContact(branch.getEmergencyContact());
        dto.setLatitude(branch.getLatitude());
        dto.setLongitude(branch.getLongitude());
        dto.setHospital(branch.getHospital().getHospitalId()); 
        dto.setCreatedAt(branch.getCreatedAt());
        dto.setUpdatedAt(branch.getUpdatedAt());
        dto.setCreatedBy(dto.getCreatedBy());
        dto.setUpdatedBy(branch.getUpdatedBy());
        return dto; 
    }

    // Convert List of Entities to List of ResponseDtos
    public List<BranchResponseDto> toDtoList(List<Branch> branches) 
    {
        return branches.stream().map(this::toDto).collect(Collectors.toList()); 
    }
}
