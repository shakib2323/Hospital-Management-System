package com.hms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hms.entity.PharmacyStock;

public interface PharmacyStockRepository extends JpaRepository<PharmacyStock, Long> {
	@Query("SELECT p FROM PharmacyStock p WHERE p.quantityAvailable <= p.minimumStockLevel")
	List<PharmacyStock> findLowStockMedicines();
	@Query("SELECT p FROM PharmacyStock p WHERE p.medicine.medicineId = :medicineId")
	Optional<PharmacyStock> findByMedicineId(@Param("medicineId") Long medicineId);
}
