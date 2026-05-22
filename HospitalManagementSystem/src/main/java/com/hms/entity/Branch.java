package com.hms.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.hms.audit.Auditable;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@Table(name = "branch")
@Entity
public class Branch extends Auditable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long branchId;

    @Column(name = "branch_name", length = 150)
    private String branchName;

    @Column(name = "branch_code", length = 50, unique = true)
    private String branchCode;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "state", length = 100)
    private String state;

    @Column(name = "country", length = 100)
    private String country;

    @Column(name = "pincode", length = 20)
    private String pincode;

    @Column(name = "address", length = 300)
    private String address;

    @Column(name = "contact_number", length = 20)
    private String contactNumber;

    @Column(name = "emergency_contact", length = 20)
    private String emergencyContact;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    // Foreign Key Hospital
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;
    
  

}
