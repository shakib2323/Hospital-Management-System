package com.hms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hms.entity.Billing;

public interface BillingRepository extends JpaRepository<Billing, Long> {
//	  @Query("SELECT b FROM Billing b WHERE b.patient.id = :patientId")
//	    List<Billing> getBillsByPatient(@Param("patientId") Long patientId);

//	  List<Billing> findByPatientId(Long patientId);
	@Query("SELECT b FROM Billing b WHERE b.patient.patientId=:id")
	List<Billing> findByPatientPatientId(Long patientId);
	  
}
