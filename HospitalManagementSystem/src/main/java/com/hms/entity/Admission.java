package com.hms.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.hms.audit.Auditable;
import com.hms.enums.AdmissionStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "admission")
public class Admission extends Auditable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admission_id")
    private Long admissionId;

    @Column(name = "admission_date", nullable = false)
    private LocalDate admissionDate;

    @Column(name = "discharge_date")
    private LocalDate dischargeDate;

    @Column(name = "admission_reason", nullable = false, length = 1000)
    private String admissionReason;

    @Enumerated(EnumType.STRING)
    @Column(name = "admission_status", nullable = false)
    private AdmissionStatus admissionStatus;

    @Column(name = "guardian_name", nullable = false)
    private String guardianName;

    @Column(name = "guardian_contact", nullable = false, length = 20)
    private String guardianContact;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bed_id", unique = true)
    private Bed bed;

}