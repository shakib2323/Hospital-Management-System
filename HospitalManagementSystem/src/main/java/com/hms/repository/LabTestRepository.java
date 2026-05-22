package com.hms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hms.entity.LabTest;
import com.hms.entity.Patient;

public interface LabTestRepository extends JpaRepository<LabTest, Long> {
	@Query("SELECT l FROM LabTest l WHERE l.patient = :patient")
	List<LabTest> findByPatient(@Param("patient") Patient patient);
}
