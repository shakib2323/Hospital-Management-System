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
@Table(name="medicine")
@Entity
public class Medicine extends Auditable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long medicineId;
	
	@Column(name="medicine_name",length = 255,nullable = false)
	private String medicineName;
	
	@Column(name="medicine_code",length = 255,nullable = false)
	private String medicineCode;
	
	@Column(name="manufacturer",length = 255,nullable = false)
	private String manufacturer;
	
	@Column(name="description",length = 255,nullable = false)
	private String description;
	
	@Column(name="dosage_form",length = 255,nullable = false)
	private String dosageForm;
	
	@Column(name="strength",length = 255,nullable = false)
	private String strength;
	
	@Column(name="unit_price")
	private Double unitPrice;
	
	@Column(name="expiry_date")
	private LocalDate expiryDate;
}
