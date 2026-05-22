package com.hms.entity;

import java.time.LocalDateTime;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.hms.audit.Auditable;
import com.hms.enums.BedStatus;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "bed")
public class Bed extends Auditable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bedId;

    @Column(name = "bed_number", length = 50)
    private String bedNumber;

    @Column(name = "bed_type", length = 50)
    private String bedType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 50)
    private BedStatus status;

    @Column(name = "ventilator_supported")
    private Boolean ventilatorSupported;

    @Column(name = "oxygen_supported")
    private Boolean oxygenSupported;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
}
