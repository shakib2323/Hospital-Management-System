package com.hms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hms.entity.LabReport;
import com.hms.entity.LabTest;

public interface LabReportRepository extends JpaRepository<LabReport, Long> {
	@Query("SELECT r FROM LabReport r WHERE r.labTest = :labTest")
	LabReport findByLabTest(@Param("labTest") LabTest labTest);
}
