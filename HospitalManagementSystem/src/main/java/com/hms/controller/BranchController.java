package com.hms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.dto.BranchRequestDto;
import com.hms.dto.BranchResponseDto;
import com.hms.dto.BranchUpdateRequestDto;
import com.hms.dto.DepartmentResponseDto;
import com.hms.response.ApiResponse;
import com.hms.service.BranchService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/branches")
@RequiredArgsConstructor
public class BranchController {

    private final BranchService branchService;
    @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
    @PostMapping("/{hospitalid}/branches")
    public ResponseEntity<ApiResponse<BranchResponseDto>> addBranch(@PathVariable Long hospitalid,@Valid @RequestBody BranchRequestDto dto) {

        BranchResponseDto response = branchService.addBranch(hospitalid, dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<BranchResponseDto>builder()
                   .success(true)
                   .message("Branch inserted successfully")
                   .statusCode(201)
                   .data(response)
                   .build());
    }
    @PreAuthorize("hasAnyRole('ADMIN','PHARMACIST','DOCTOR','PHARMACIST','NURSE','HR_MANAGER')")
    @PatchMapping("/{branchId}")
    public ResponseEntity<ApiResponse<BranchResponseDto>> updateBranch(@PathVariable Long branchId,@Valid @RequestBody BranchUpdateRequestDto dto) 
    {
        BranchResponseDto branch = branchService.updateBranch(branchId, dto);
        return ResponseEntity.ok(ApiResponse.<BranchResponseDto>builder()
                .success(true)
                .message("Branch updated successfully")
                .statusCode(200)
                .data(branch)
                .build());
    }
    @GetMapping("/{branchid}")
    public ResponseEntity<ApiResponse<BranchResponseDto>> findById(@PathVariable Long branchid) {

        BranchResponseDto branch = branchService.getBranchById(branchid);
        return ResponseEntity.ok(ApiResponse.<BranchResponseDto>builder()
                .success(true)
                .message("Branch fetched successfully")
                .statusCode(200)
                .data(branch)
                .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<BranchResponseDto>>> findAllBranches() {
        List<BranchResponseDto> branches = branchService.getAllBranches();
        return ResponseEntity.ok(ApiResponse.<List<BranchResponseDto>>builder()
                .success(true)
                .message("Branches fetched successfully")
                .statusCode(200)
                .data(branches)
                .build());
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{branchid}")
    public ResponseEntity<ApiResponse<String>> deleteBranchById(@PathVariable Long branchid)
    {
    	 String message = branchService.removeBranch(branchid);

    	    return ResponseEntity.ok(
    	            ApiResponse.<String>builder()
    	                    .success(true)
    	                    .message(message)
    	                    .statusCode(200)
    	                    .data(null)
    	                    .build()
    	    );
    }
    @GetMapping("/{hospitalid}/{branchid}")
    public ResponseEntity<ApiResponse<BranchResponseDto>> findBranchbyBranch_HospitalID(@PathVariable Long hospitalid,@PathVariable Long branchid)
    {
    	BranchResponseDto branch=branchService.findBranchByHospital_BranchId(hospitalid, branchid);
    	 return ResponseEntity.ok(
 	            ApiResponse.<BranchResponseDto>builder()
 	                    .success(true)
 	                    .message("Branch Found seccussfully")
 	                    .statusCode(200)
 	                    .data(branch)
 	                    .build());
   
    	
    }
    @GetMapping("/{branchId}/department")
    public ResponseEntity<ApiResponse<List<DepartmentResponseDto>>> findDepartmentByBranchId(@PathVariable Long branchId)
    {
    	List<DepartmentResponseDto> department=branchService.getBranchDepartments(branchId);
    	ApiResponse<List<DepartmentResponseDto>> response=ApiResponse.<List<DepartmentResponseDto>>builder()
    			.success(true)
    			.message("Department fetched successfully!")
    			.statusCode(200)
    			.data(department)
    			.build();
    	return ResponseEntity.ok(response);
    }
}