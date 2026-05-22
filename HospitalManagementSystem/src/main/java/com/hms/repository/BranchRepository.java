package com.hms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.hms.entity.Branch;

import jakarta.transaction.Transactional;

public interface BranchRepository extends JpaRepository<Branch, Long> {
  
	@Modifying
	@Transactional
	@Query("DELETE FROM Branch WHERE branchId = ?1")
	public void deleteBranchById(Long id);
	@Query("SELECT b FROM Branch b WHERE b.hospital.hospitalId = ?1 AND b.branchId = ?2")
	Optional<Branch> findByHospital_HospitalIdAndBranchId(Long hospitalId, Long branchId);
}
