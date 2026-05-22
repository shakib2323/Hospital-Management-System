package com.hms.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.hms.audit.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Table(name = "lab_report")
@Entity
public class LabReport extends Auditable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;
    @Column(name="report_date")
    private LocalDate reportDate;
    @Column(name="result_summary",columnDefinition = "TEXT")
    private String resultSummary;
    @Column(name="observations",columnDefinition = "TEXT")
    private String observations;
    @Column(name="recommendation",columnDefinition = "TEXT")
    private String recommendation;
    @Column(name="technican_name")
    private String technicianName;
    @Column(name="approved_by")
    private String approvedBy;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lab_test_id", nullable = false)
    private LabTest labTest;             
}
