package com.hms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hms.dto.PharmacyStockRequestDto;
import com.hms.dto.PharmacyStockResponseDto;
import com.hms.dto.PharmacyStockUpdateRequestDto;
import com.hms.entity.Medicine;
import com.hms.entity.PharmacyStock;
import com.hms.exception.ResourceNotFoundException;
import com.hms.mapper.PharmacyStockMapper;
import com.hms.repository.MedicineRepository;
import com.hms.repository.PharmacyStockRepository;
import com.hms.service.PharmacyStockService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class PharmacyStockServiceImpl implements PharmacyStockService
{
    private final PharmacyStockRepository pharmacyStockRepo;
    private final PharmacyStockMapper pharmacyStockMapper;
    private final MedicineRepository medicineRepo;
	@Override
	public PharmacyStockResponseDto addStock(PharmacyStockRequestDto dto) {
		PharmacyStock pharmacyStock=pharmacyStockMapper.toEntity(dto);
		pharmacyStockRepo.save(pharmacyStock);
		return pharmacyStockMapper.toDto(pharmacyStock);
	}

	@Override
	public PharmacyStockResponseDto updateStock(Long stockId, PharmacyStockUpdateRequestDto  dto) {
		PharmacyStock pharmacyStock=pharmacyStockRepo.findById(stockId).orElseThrow(()->
		new ResourceNotFoundException("Stock not found with id: "+stockId));
		pharmacyStock.setQuantityAvailable(dto.getQuantityAvailable());
		pharmacyStock.setMinimumStockLevel(dto.getMinimumStockLevel());
		pharmacyStock.setMaximumStockLevel(dto.getMaximumStockLevel());
		pharmacyStock.setExpiryDate(dto.getExpiryDate());
		pharmacyStock.setSupplierName(dto.getSupplierName());
		pharmacyStock.setStorageLocation(dto.getStorageLocation());
		pharmacyStock.setUpdatedBy(dto.getUpdatedBy());
		pharmacyStockRepo.save(pharmacyStock);
		return pharmacyStockMapper.toDto(pharmacyStock);
	}

	@Override
	public PharmacyStockResponseDto getStockById(Long stockId) {
		PharmacyStock pharmacyStock=pharmacyStockRepo.findById(stockId).orElseThrow(()->
		new ResourceNotFoundException("Stock not found with id: "+stockId));
		return pharmacyStockMapper.toDto(pharmacyStock);
	}

	@Override
	public List<PharmacyStockResponseDto> getLowStockMedicines() {
		 List<PharmacyStock> lowStocks = pharmacyStockRepo.findLowStockMedicines();
		    return pharmacyStockMapper.toDtoList(lowStocks);
	}

	
	@Override
	public void reduceMedicineStock(Long medicineId, Integer quantity) {   
	    PharmacyStock stock = pharmacyStockRepo.findByMedicineId(medicineId)
	            .orElseThrow(() -> new ResourceNotFoundException("Stock not found for medicine id: " + medicineId));
	    if (stock.getQuantityAvailable() < quantity) {
	        throw new IllegalStateException("Insufficient stock Available: "+ stock.getQuantityAvailable()
	                + " Requested: " + quantity);
	    }
	    stock.setQuantityAvailable(stock.getQuantityAvailable() - quantity);
	    if (stock.getQuantityAvailable() <= stock.getMinimumStockLevel()) {
	        System.out.println("Warning: Stock for medicine id " + medicineId + " is below minimum level!");
	    }
	    pharmacyStockRepo.save(stock);
	}

	@Override
	public void deleteStock(Long stockId) {
		PharmacyStock pharmacyStock=pharmacyStockRepo.findById(stockId).orElseThrow(()->
		new ResourceNotFoundException("Stock not found with id: "+stockId));
		pharmacyStockRepo.delete(pharmacyStock);
		
	}

	

}
