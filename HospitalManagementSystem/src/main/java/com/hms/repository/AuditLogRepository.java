package com.hms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hms.audit.AuditLog;
@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
	    @Query("SELECT a FROM AuditLog a WHERE a.username = :username")
	    List<AuditLog> findByUsername(@Param("username") String username);

	    // Find all logs by entity name
	    @Query("SELECT a FROM AuditLog a WHERE a.entityName = :entityName")
	    List<AuditLog> findByEntityName(@Param("entityName") String entityName);

	    // Find all logs by action
	    @Query("SELECT a FROM AuditLog a WHERE a.action = :action")
	    List<AuditLog> findByAction(@Param("action") String action);
}
