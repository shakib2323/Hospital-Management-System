package com.hms.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.hms.audit.Auditable;
import com.hms.enums.DoctorAvailabilityStatus;
import com.hms.enums.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "doctor")
public class Doctor extends Auditable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;

    @Column(name = "doctor_code", length = 50)
    private String doctorCode;

    @Column(name = "first_name", length = 100)
    private String firstName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 20)
    private Gender gender;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "qualification", length = 200)
    private String qualification;

    @Column(name = "experience_years")
    private Integer experienceYears;

    @Column(name = "consultation_fee")
    private Double consultationFee;

    @Column(name = "license_number", length = 100)
    private String licenseNumber;

    @Column(name = "special_note", length = 1000)
    private String specialNote;

    @Column(name = "availability_start_time")
    private LocalTime availabilityStartTime;

    @Column(name = "availability_end_time")
    private LocalTime availabilityEndTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 50)
    private DoctorAvailabilityStatus status;

    //  ManyToOne  doctor belongs to one department
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    //  OneToOne  doctor has one user account
    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @ManyToMany
    @JoinTable( name = "doctor_specialization",joinColumns = @JoinColumn(name = "doctor_id"),
        inverseJoinColumns = @JoinColumn(name = "specialization_id")
    )
    private Set<Specialization> specializations = new HashSet<>();
}
