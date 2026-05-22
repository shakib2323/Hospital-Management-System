package com.hms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hms.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
	@Query("SELECT p FROM Payment p WHERE p.transactionId = :transactionId")
	Optional<Payment> findByTransactionId(@Param("transactionId") String transactionId);
}
