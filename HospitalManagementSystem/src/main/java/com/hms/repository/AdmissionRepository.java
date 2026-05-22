package com.hms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hms.entity.Admission;
import com.hms.entity.Patient;
import com.hms.enums.AdmissionStatus;

public interface AdmissionRepository extends JpaRepository<Admission, Long> {
	@Query("SELECT a FROM Admission a WHERE a.admissionStatus = :status")
	List<Admission> findByAdmissionStatus(@Param("status") AdmissionStatus active);
	@Query("SELECT a FROM Admission a WHERE a.patient = :patient")
	List<Admission> findByPatient(@Param("patient") Patient patient);
	@Query("SELECT a FROM Admission a WHERE a.patient = :patient AND a.admissionStatus = 'ACTIVE'")
	Admission findActiveAdmissionByPatient(@Param("patient") Patient patient);
}
