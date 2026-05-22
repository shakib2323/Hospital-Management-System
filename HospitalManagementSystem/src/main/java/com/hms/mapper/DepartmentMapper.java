package com.hms.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.hms.dto.DepartmentRequestDto;
import com.hms.dto.DepartmentResponseDto;
import com.hms.entity.Branch;
import com.hms.entity.Department;
import com.hms.exception.ResourceNotFoundException;
import com.hms.repository.BranchRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DepartmentMapper 
{
    private final BranchRepository branchRepo;
    // Convert RequestDto to Entity
    public Department toEntity(DepartmentRequestDto dto) 
    {
        Department department = new Department();
        department.setDepartmentName(dto.getDepartmentName());
        department.setDepartmentCode(dto.getDepartmentCode());
        department.setDescription(dto.getDescription());
        department.setFloorNumber(dto.getFloorNumber());
        department.setContactExtension(dto.getContactExtension());
        // Fetch branch and SET it on department
        Branch branch = branchRepo.findById(dto.getBranchId())
                .orElseThrow(() -> new ResourceNotFoundException("Branch Not found!"));
        department.setBranch(branch);
        department.setCreatedBy(dto.getCreatedBy());
        department.setUpdatedBy(dto.getUpdatedBy());
        return department;
    }

    // Convert Entity to ResponseDto
    public DepartmentResponseDto toDto(Department department) 
    {
        DepartmentResponseDto dto = new DepartmentResponseDto();
        dto.setDepartmentName(department.getDepartmentName());
        dto.setDepartmentCode(department.getDepartmentCode());
        dto.setDescription(department.getDescription());
        dto.setFloorNumber(department.getFloorNumber());
        dto.setContactExtension(department.getContactExtension());
        dto.setBranchId(department.getBranch().getBranchId());
        dto.setBranchName(department.getBranch().getBranchName());
        dto.setCreatedAt(department.getCreatedAt());
        dto.setUpdatedAt(dto.getUpdatedAt());
        dto.setCreatedBy(department.getCreatedBy());
        dto.setUpdatedBy(department.getUpdatedBy());
        return dto;
    }
    // Convert List<Entity> to List<ResponseDto>
    public List<DepartmentResponseDto> toDtoList(List<Department> departments) 
    {
        return departments.stream().map(this::toDto).collect(Collectors.toList());
    }
}