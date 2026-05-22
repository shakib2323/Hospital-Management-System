package com.hms.service;

import java.util.List;

import com.hms.dto.BranchRequestDto;
import com.hms.dto.BranchResponseDto;
import com.hms.dto.BranchUpdateRequestDto;
import com.hms.dto.DepartmentResponseDto;
import com.hms.dto.DoctorResponseDto;

public interface BranchService
{
	public BranchResponseDto addBranch(Long hospitalId,BranchRequestDto dto);
    public BranchResponseDto updateBranch(Long branchId,BranchUpdateRequestDto dto);
    public BranchResponseDto getBranchById( Long branchId);
    public List<BranchResponseDto> getAllBranches();
    public List<DepartmentResponseDto> getBranchDepartments(Long branchId);
    public List<DoctorResponseDto> getBranchDoctors(Long branchId);
    public String removeBranch(Long branchId);
    public BranchResponseDto findBranchByHospital_BranchId(Long hId,Long bId);
}
