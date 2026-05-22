package com.hms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hms.entity.Branch;
import com.hms.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
	@Query("SELECT d FROM Department d WHERE d.branch = :branch")
	List<Department> findByBranch(@Param("branch") Branch branch);
}
