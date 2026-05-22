package com.hms.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.hms.audit.Auditable;
import com.hms.enums.AmbulanceStatus;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ambulance")
public class Ambulance extends Auditable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ambulanceId;

    @Column(name = "vehicle_number", nullable = false, length = 50)
    private String vehicleNumber;

    @Column(name = "driver_name", nullable = false, length = 100)
    private String driverName;

    @Column(name = "driver_phone", unique = true, nullable = false, length = 20)
    private String driverPhone;

    @Enumerated(EnumType.STRING)
    @Column(name = "availability_status", length = 50)
    private AmbulanceStatus availabilityStatus;

    @Column(name = "current_location", nullable = false, length = 200)
    private String currentLocation;

    @Column(name = "vehicle_type", length = 100, nullable = false)
    private String vehicleType;

    //ManyToOne ambulance belongs to one branch
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;
}
