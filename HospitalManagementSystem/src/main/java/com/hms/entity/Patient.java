package com.hms.entity;


import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.hms.audit.Auditable;
import com.hms.enums.BloodGroup;
import com.hms.enums.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Entity
@Table(name = "patient")
public class Patient extends Auditable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;

    @Column(name = "patient_code", unique = true, nullable = false, length = 50)
    private String patientCode;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 20)
    private Gender gender;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "blood_group", length = 20)
    private BloodGroup bloodGroup;

    @Column(name = "marital_status", length = 50)
    private String maritalStatus;

    @Column(name = "occupation", length = 100)
    private String occupation;

    @Column(name = "height")                
    private Double height;

    @Column(name = "weight")                 
    private Double weight;

    @Column(name = "allergies", length = 1000) 
    private String allergies;

    @Column(name = "emergency_contact_name", length = 100) 
    private String emergencyContactName;

    @Column(name = "emergency_contact_number", length = 20)  
    private String emergencyContactNumber;

    @Column(name = "insurance_provider", length = 100)
    private String insuranceProvider;

    @Column(name = "insurance_policy_number", unique = true, length = 100)
    private String insurancePolicyNumber;

    @Column(name = "address", length = 300)
    private String address;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "state", length = 100)
    private String state;

    @Column(name = "pincode", length = 20)
    private String pincode;

    //OneToOne association  not raw User type
    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;
}
