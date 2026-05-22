package com.hms.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hms.dto.MedicineRequestDto;
import com.hms.dto.MedicineResponseDto;
import com.hms.dto.MedicineUpdateRequestDto;
import com.hms.entity.Medicine;
import com.hms.exception.ResourceNotFoundException;
import com.hms.mapper.MedicineMapper;
import com.hms.repository.MedicineRepository;
import com.hms.service.MedicineService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class MedicineServiceImpl implements MedicineService
{
   private final MedicineMapper medicineMapper;
   private final MedicineRepository medicineRepo;
   
	@Override
	public MedicineResponseDto addMedicine(MedicineRequestDto dto) {
		Medicine medicine=medicineMapper.toEntity(dto);
		medicineRepo.save(medicine);
		return medicineMapper.toDto(medicine);
	}

	@Override
	public MedicineResponseDto updateMedicine(Long medicineId, MedicineUpdateRequestDto  dto) {
		Medicine medicine=medicineRepo.findById(medicineId).orElseThrow(()->
		new ResourceNotFoundException("Medicine not found with id: "+medicineId));

		medicine.setMedicineName(dto.getMedicineName());
		medicine.setManufacturer(dto.getManufacturer());
		medicine.setDescription(dto.getDescription());
		medicine.setDosageForm(dto.getDosageForm());
		medicine.setStrength(dto.getStrength());
		medicine.setExpiryDate(dto.getExpiryDate());
		medicineRepo.save(medicine);
		return medicineMapper.toDto(medicine);
	}

	@Override
	public MedicineResponseDto getMedicineById(Long medicineId) {
		Medicine medicine=medicineRepo.findById(medicineId).orElseThrow(()->
		new ResourceNotFoundException("Medicine not found with id: "+medicineId));
		return medicineMapper.toDto(medicine);
	}

	@Override
	public List<MedicineResponseDto> getAllMedicines() {
		List<Medicine> medicines=medicineRepo.findAll();
		return medicineMapper.toDtoList(medicines);
	}

	@Override
	public List<MedicineResponseDto> searchMedicineByName(String medicineName) {
		List<Medicine> medicine=medicineRepo.findByMedicineName(medicineName);
		return medicineMapper.toDtoList(medicine);
	}

	@Override
	public void deleteMedicine(Long medicineId) {
		Medicine medicine=medicineRepo.findById(medicineId).orElseThrow(()->
		new ResourceNotFoundException("Medicine not found with id: "+medicineId));
		medicineRepo.delete(medicine);	
	}

	

}
