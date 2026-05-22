package com.hms.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.hms.dto.PharmacyStockRequestDto;
import com.hms.dto.PharmacyStockResponseDto;
import com.hms.entity.Medicine;
import com.hms.entity.PharmacyStock;
import com.hms.exception.ResourceNotFoundException;
import com.hms.repository.MedicineRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PharmacyStockMapper
{
    private final MedicineRepository medicineRepo;
    public PharmacyStock toEntity(PharmacyStockRequestDto dto) 
    {
        PharmacyStock pharmacyStock = new PharmacyStock();
        pharmacyStock.setBatchNumber(dto.getBatchNumber());
        pharmacyStock.setQuantityAvailable(dto.getQuantityAvailable());
        pharmacyStock.setMinimumStockLevel(dto.getMinimumStockLevel());
        pharmacyStock.setMaximumStockLevel(dto.getMaximumStockLevel());
        pharmacyStock.setPurchaseDate(dto.getPurchaseDate());
        pharmacyStock.setExpiryDate(dto.getExpiryDate());
        pharmacyStock.setSupplierName(dto.getSupplierName());
        pharmacyStock.setStorageLocation(dto.getStorageLocation());
        Medicine medicine = medicineRepo.findById(dto.getMedicineId())
                .orElseThrow(() -> new ResourceNotFoundException("Medicine not found with id: " + dto.getMedicineId()));
        pharmacyStock.setMedicine(medicine);
        pharmacyStock.setCreatedBy(dto.getCreatedBy());
        pharmacyStock.setUpdatedBy(dto.getUpdatedBy());
        return pharmacyStock;
    }

    public PharmacyStockResponseDto toDto(PharmacyStock pharmacyStock)
    { 
        PharmacyStockResponseDto dto = new PharmacyStockResponseDto();
        dto.setStockId(pharmacyStock.getStockId());
        dto.setBatchNumber(pharmacyStock.getBatchNumber());
        dto.setQuantityAvailable(pharmacyStock.getQuantityAvailable());
        dto.setMinimumStockLevel(pharmacyStock.getMinimumStockLevel());
        dto.setMaximumStockLevel(pharmacyStock.getMaximumStockLevel());
        dto.setPurchaseDate(pharmacyStock.getPurchaseDate());
        dto.setExpiryDate(pharmacyStock.getExpiryDate());
        dto.setSupplierName(pharmacyStock.getSupplierName());
        dto.setStorageLocation(pharmacyStock.getStorageLocation());
        dto.setMedicineId(pharmacyStock.getMedicine().getMedicineId());
        dto.setMedicineName(pharmacyStock.getMedicine().getMedicineName());
        dto.setCreatedBy(pharmacyStock.getCreatedBy());
        dto.setUpdatedBy(pharmacyStock.getUpdatedBy());
        dto.setCreatedAt(pharmacyStock.getCreatedAt());  
        dto.setUpdatedAt(pharmacyStock.getUpdatedAt());  
        return dto;                                      
    }

    public List<PharmacyStockResponseDto> toDtoList(List<PharmacyStock> stocks)
    {  
        return stocks.stream().map(this::toDto).collect(Collectors.toList());
    }
}
