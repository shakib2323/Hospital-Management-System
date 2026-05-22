package com.hms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.entity.MedicalRecord;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {

}
