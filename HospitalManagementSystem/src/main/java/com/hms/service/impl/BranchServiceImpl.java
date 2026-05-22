package com.hms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hms.dto.BranchRequestDto;
import com.hms.dto.BranchResponseDto;
import com.hms.dto.BranchUpdateRequestDto;
import com.hms.dto.DepartmentResponseDto;
import com.hms.dto.DoctorResponseDto;
import com.hms.entity.Branch;
import com.hms.entity.Department;
import com.hms.entity.Hospital;
import com.hms.exception.InvalidOperationException;
import com.hms.exception.ResourceNotFoundException;
import com.hms.mapper.BranchMapper;
import com.hms.mapper.DepartmentMapper;
import com.hms.repository.BranchRepository;
import com.hms.repository.DepartmentRepository;
import com.hms.repository.HospitalRepository;
import com.hms.service.BranchService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService
{
    private final HospitalRepository hospitalRepo;
	private final BranchRepository branchRepo;
	private final BranchMapper branchmapper;
	private final DepartmentRepository departmentRepo;
	private final DepartmentMapper departmentMapper;
	@Override
	public BranchResponseDto addBranch(Long hospitalId, BranchRequestDto dto) 
	{
		Hospital hospital=hospitalRepo.findById(hospitalId)
				.orElseThrow(()->new ResourceNotFoundException("Hospital not found with id: "+hospitalId));
		Branch branch=branchmapper.toEntity(dto);
		branch.setHospital(hospital);
		 Branch savedBranch =branchRepo.save(branch);
		return branchmapper.toDto(savedBranch);
	}

	@Override
	public BranchResponseDto updateBranch(Long branchId, BranchUpdateRequestDto dto) 
	{
		Branch branch=branchRepo.findById(branchId)
				.orElseThrow(()->new ResourceNotFoundException("Branch not found on this id: "+branchId));
		Map<String,String> map=new HashMap<>();
		if(dto.getBranchCode()!=null)
		{
			map.put("branchCode:", "Not Updatable fields!");
		}
		if(dto.getContactNumber()!=null)
		{
			map.put("contactNumber", "Not Updatable field!");
			
		}
		if(dto.getEmergencyContact()!=null)
		{
			map.put("emergencyContact", "Not Updatable field!");
		
		}
//		if(dto.getHospitalId()!=null)
//		{
//			map.put("HospitalId:", "Not Updatable fields!");
//		}
		if(dto.getLatitude()!=null)
		{
			map.put("Latitude:", "Not Updatable fields!");
		}
		if(dto.getLongitude()!=null)
		{
			map.put("Longitude:", "Not Updatable fields!");
		}
		if(!map.isEmpty())
		{
			 throw new InvalidOperationException((Map<String, String>) map);
		}
		branch.setBranchName(dto.getBranchName());
		branch.setCity(dto.getCity());
		branch.setState(dto.getState());  
		branch.setCountry(dto.getCountry());
		branch.setAddress(dto.getAddress());
		branch.setPincode(dto.getPincode()); 
		Branch updatedBranch=branchRepo.save(branch);
		BranchResponseDto responseDto=branchmapper.toDto(updatedBranch);
		return responseDto;
	}

	@Override
	public BranchResponseDto getBranchById(Long branchId) 
	{
		Branch branch=branchRepo.findById(branchId).orElseThrow(()
		->new ResourceNotFoundException("Branch not found with id: "+branchId));
		return branchmapper.toDto(branch);
	}

	@Override
	public List<BranchResponseDto> getAllBranches()
	{
		List<Branch> branch=branchRepo.findAll();
		
		return branchmapper.toDtoList(branch);
	}

	@Override
	public List<DepartmentResponseDto> getBranchDepartments(Long branchId)
	{
		Branch branch=branchRepo.findById(branchId).orElseThrow(()
				->new ResourceNotFoundException("Department not found with id: "+branchId));
		List<Department> departments = departmentRepo.findByBranch(branch);
		return departmentMapper.toDtoList(departments);
	}

	@Override
	public List<DoctorResponseDto> getBranchDoctors(Long branchId) {
		
		return null;
	}

	@Override
	public String removeBranch(Long branchId) {
		  Branch branch = branchRepo.findById(branchId)
		            .orElseThrow(() ->
		                    new ResourceNotFoundException("Branch not found with id : " + branchId));
		branchRepo.delete(branch);
		return "Branch Deleted Successfully";
		
	}

	@Override
	public BranchResponseDto findBranchByHospital_BranchId(Long hId, Long bId) {

	    Branch branch = branchRepo
	            .findByHospital_HospitalIdAndBranchId(hId, bId)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException("Branch not found with id : " +" Hid"+ hId+" bid "+bId));

	    return branchmapper.toDto(branch);
	}

}
