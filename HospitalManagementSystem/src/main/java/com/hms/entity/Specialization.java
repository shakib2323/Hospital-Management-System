package com.hms.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.hms.audit.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@RequiredArgsConstructor
@Table(name="Specialization")
@Entity
public class Specialization extends Auditable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long specializationId;
	@Column(name="specialization_name",length = 255,nullable = false)
	private String specializationName;
	@Column(name="description",length = 255,nullable = false)
	private String description;
	
}
