package com.hms.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.hms.audit.Auditable;
import com.hms.enums.LabTestStatus;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "lab_test")
public class LabTest extends Auditable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long labTestId;

    @Column(name = "test_name", length = 150)
    private String testName;

    @Column(name = "test_code", length = 50)
    private String testCode;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "test_cost")
    private Double testCost;

    @Column(name = "normal_range", length = 200)
    private String normalRange;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 50)
    private LabTestStatus status;

    @Column(name = "sample_type", length = 100)
    private String sampleType;

    //ManyToOne  patient
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    //ManyToOne doctor
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

}