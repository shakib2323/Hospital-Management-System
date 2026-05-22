package com.hms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hms.dto.LabTestRequestDto;
import com.hms.dto.LabTestResponseDto;
import com.hms.entity.LabTest;
import com.hms.enums.LabTestStatus;
import com.hms.exception.ResourceNotFoundException;
import com.hms.mapper.LabTestMapper;
import com.hms.repository.LabTestRepository;
import com.hms.service.LabTestService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class LabTestServiceImpl implements LabTestService
{
   private final LabTestRepository labTestRepo;
   private final LabTestMapper labTestMapper;
	@Override
	public LabTestResponseDto createLabTest(LabTestRequestDto dto) {
		LabTest labtest=labTestMapper.toEntity(dto);
		labTestRepo.save(labtest);
		return labTestMapper.toDto(labtest);
	}

	@Override
	public LabTestResponseDto updateLabTest(Long labTestId, LabTestRequestDto dto) {
        LabTest labtest=labTestRepo.findById(labTestId).orElseThrow(()->
        new ResourceNotFoundException("LabTest not found with id: "+labTestId));
        LabTest updatelabtest=labTestMapper.updateEntity(labtest, dto);
        labTestRepo.save(updatelabtest);
		return labTestMapper.toDto(updatelabtest);
	}

	@Override
	public LabTestResponseDto getLabTestById(Long labTestId) {
		  LabTest labtest=labTestRepo.findById(labTestId).orElseThrow(()->
	        new ResourceNotFoundException("LabTest not found with id: "+labTestId));
		return labTestMapper.toDto(labtest);
	}

	@Override
	public List<LabTestResponseDto> getAllLabTests() {
		List<LabTest> labTests=labTestRepo.findAll();
		return labTestMapper.toDtoList(labTests);
	}

	@Override
	public void assignLabTechnician(Long testId, String technicianName) {
	    LabTest labTest = labTestRepo.findById(testId)
	            .orElseThrow(() -> new ResourceNotFoundException("LabTest not found with id: " + testId));
	    labTest.setStatus(LabTestStatus.IN_PROGRESS);
	    labTestRepo.save(labTest);
	}

	@Override
	public void cancelLabTest(Long labTestId) {
		 LabTest labtest=labTestRepo.findById(labTestId).orElseThrow(()->
	        new ResourceNotFoundException("LabTest not found with id: "+labTestId));
		 if (labtest.getStatus().equals(LabTestStatus.CANCELLED)) {
			    throw new IllegalStateException("Lab test is already cancelled with id: " + labTestId);
			}
			//Also throw if already completed — cannot cancel completed test
			if (labtest.getStatus().equals(LabTestStatus.COMPLETED)) {
			    throw new IllegalStateException("Cannot cancel a completed lab test with id: " + labTestId);
			}
		labtest.setStatus(LabTestStatus.CANCELLED);
		labTestRepo.save(labtest);
	}

}
