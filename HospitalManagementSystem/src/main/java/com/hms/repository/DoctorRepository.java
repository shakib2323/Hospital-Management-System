package com.hms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hms.entity.Department;
import com.hms.entity.Doctor;
import com.hms.enums.DoctorAvailabilityStatus;

public interface DoctorRepository extends JpaRepository<Doctor, Long> 
{
	@Query("SELECT d FROM Doctor d JOIN d.specializations s WHERE s.specializationName = ?1")
	List<Doctor> findDoctorsBySpecialization(String specializationName);
	List<Doctor> findByStatus(DoctorAvailabilityStatus status);
	@Query("SELECT d FROM Doctor d WHERE d.department = :department")
	List<Doctor> findByDepartment(@Param("department") Department department);
}
