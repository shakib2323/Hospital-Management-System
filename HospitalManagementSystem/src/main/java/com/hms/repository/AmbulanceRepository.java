package com.hms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hms.entity.Ambulance;
import com.hms.enums.AmbulanceStatus;

public interface AmbulanceRepository extends JpaRepository<Ambulance, Long> {

	List<Ambulance> findByAvailabilityStatus(AmbulanceStatus availabilityStatus);
}
