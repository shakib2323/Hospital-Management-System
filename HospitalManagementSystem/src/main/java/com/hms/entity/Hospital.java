package com.hms.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.hms.audit.Auditable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Table(name = "hospital")
@Entity
@RequiredArgsConstructor
public class Hospital extends Auditable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hospitalId;

    @NotNull
    @Column(name = "hospital_name", length = 150, nullable = false) 
    private String hospitalName;

    @NotNull
    @Column(name = "hospital_code", length = 50, nullable = false, unique = true)
    private String hospitalCode;

    @NotNull
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotNull
    @Column(name = "phone_number", length = 20, nullable = false)
    private String phoneNumber;

    @Column(name = "website", length = 150)
    private String website;

    @NotNull
    @Column(name = "registration_number", unique = true, length = 100, nullable = false) 
    private String registrationNumber;

    @Column(name = "established_year")
    private Integer establishedYear;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "active")
    private Boolean active = true; 

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Branch> branches;
}