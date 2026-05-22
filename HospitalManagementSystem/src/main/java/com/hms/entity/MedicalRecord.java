package com.hms.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.hms.audit.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "medical_record")
public class MedicalRecord extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medicalRecordId;
    @Column(name = "chronic_diseases", length = 1000)
    private String chronicDiseases;
    @Column(name = "past_surgeries", length = 1000)
    private String pastSurgeries;
    @Column(name = "family_medical_history", length = 1000)
    private String familyMedicalHistory;
    @Column(name = "current_medications", length = 1000)
    private String currentMedications;
    @Column(name = "vaccination_details", length = 1000)
    private String vaccinationDetails;
    @Column(name = "smoking_habit")
    private Boolean smokingHabit;
    @Column(name = "alcohol_consumption")
    private Boolean alcoholConsumption;
    @Column(name = "organ_donor")
    private Boolean organDonor;
    @Column(name = "remarks", length = 2000)
    private String remarks;
    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "patient_id", unique = true)
    private Patient patient;
}