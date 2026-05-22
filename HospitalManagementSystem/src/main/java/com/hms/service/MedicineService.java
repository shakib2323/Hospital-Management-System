package com.hms.service;

import java.util.List;

import com.hms.dto.MedicineRequestDto;
import com.hms.dto.MedicineResponseDto;
import com.hms.dto.MedicineUpdateRequestDto;

public interface MedicineService
{
	public MedicineResponseDto addMedicine(MedicineRequestDto dto);
	public MedicineResponseDto updateMedicine(Long medicineId,MedicineUpdateRequestDto  dto);
	public MedicineResponseDto getMedicineById(Long medicineId);
	public List<MedicineResponseDto> getAllMedicines();
	public List<MedicineResponseDto>searchMedicineByName(String medicineName);
	public void deleteMedicine(Long medicineId);
}
