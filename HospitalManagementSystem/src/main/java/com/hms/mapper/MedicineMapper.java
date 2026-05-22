package com.hms.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.hms.dto.MedicineRequestDto;
import com.hms.dto.MedicineResponseDto;
import com.hms.entity.Medicine;

import lombok.Data;

@Component
public class MedicineMapper
{
 public Medicine toEntity(MedicineRequestDto dto)
 {
	 Medicine medicine=new Medicine();
	 medicine.setMedicineName(dto.getMedicineName());
	 medicine.setMedicineCode(dto.getMedicineCode());
	 medicine.setManufacturer(dto.getManufacturer());
	 medicine.setDescription(dto.getDescription());
	 medicine.setDosageForm(dto.getDosageForm());
	 medicine.setStrength(dto.getStrength());
	 medicine.setUnitPrice(dto.getUnitPrice());
	 medicine.setExpiryDate(dto.getExpiryDate());
	 medicine.setCreatedBy(dto.getCreatedBy());
	 medicine.setUpdatedBy(dto.getUpdatedBy());
	 return medicine;
 }
 public MedicineResponseDto toDto(Medicine medicine)
 {   MedicineResponseDto dto=new MedicineResponseDto();
     dto.setMedicineId(dto.getMedicineId());
     dto.setMedicineName(medicine.getMedicineName());
     dto.setMedicineCode(medicine.getMedicineCode());
     dto.setManufacturer(medicine.getManufacturer());
     dto.setDescription(medicine.getDescription());
     dto.setDosageForm(medicine.getDosageForm());
     dto.setStrength(medicine.getStrength());
     dto.setUnitPrice(medicine.getUnitPrice());
     dto.setExpiryDate(medicine.getExpiryDate());
     dto.setCreatedBy(medicine.getCreatedBy());
     dto.setUpdatedBy(medicine.getUpdatedBy());
	 return dto;
 }
 public List<MedicineResponseDto> toDtoList(List<Medicine> medicine)
 {
	 return medicine.stream().map(this::toDto).collect(Collectors.toList());
 }
}
