package com.hms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.hms.entity.Patient;
import com.hms.entity.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
//	List<Prescription> patientPrescriptions(Patient patient);
	List<Prescription> findByPatient(@Param("patient") Patient patient);
//	@Query("SELECT p FROM Prescription p WHERE p.patient = :patient")
//	List<Prescription> findByPatient(@Param("patient") Patient patient);
}
