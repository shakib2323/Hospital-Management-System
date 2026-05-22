package com.hms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.entity.Medicine;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
//	public List<Medicine> findMedicineByName(String medicineName);
	 List<Medicine> findByMedicineName(String medicineName);

	    Optional<Medicine> findByMedicineCode(String medicineCode);
}
