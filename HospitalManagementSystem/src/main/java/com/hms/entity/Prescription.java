package com.hms.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.hms.audit.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Data
@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "prescription")
public class Prescription extends Auditable 
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prescriptionId;

    @Column(name = "diagnosis", length = 2000)      
    private String diagnosis;

    @Column(name = "doctor_notes", length = 2000)     
    private String doctorNotes;

    @Column(name = "follow_up_date")
    private LocalDate followUpDate;

    @Column(name = "prescribed_date")
    private LocalDate prescribedDate;

    //  OneToOne 
    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "appointment_id", unique = true)
    private Appointment appointment;

    // ManyToOne 
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    // ManyToOne 
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    @OneToMany
    @JoinColumn(name = "prescription_id")
    private List<Medicine> medicines;
}