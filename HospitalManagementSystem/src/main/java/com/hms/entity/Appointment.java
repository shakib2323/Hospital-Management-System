package com.hms.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.hms.audit.Auditable;
import com.hms.enums.AppointmentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "appointment")
public class Appointment extends Auditable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    @Column(name = "appointment_number", length = 50)
    private String appointmentNumber;

    @Column(name = "appointment_date")
    private LocalDate appointmentDate;

    @Column(name = "appointment_time")
    private LocalTime appointmentTime;

    @Column(name = "symptoms", length = 1000)
    private String symptoms;

    @Column(name = "priority_level", length = 50)
    private String priorityLevel;

    @Column(name = "token_number")
    private Integer tokenNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 50)
    private AppointmentStatus status;

    @Column(name = "remarks", length = 2000)
    private String remarks;

    // ManyToOne associations
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "department_id") 
    private Department department;

}