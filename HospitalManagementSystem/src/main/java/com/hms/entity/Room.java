package com.hms.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.hms.audit.Auditable;
import com.hms.enums.RoomType;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "room")
public class Room extends Auditable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    @Column(name = "room_number", length = 50)
    private String roomNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type", length = 50)
    private RoomType roomType;

    @Column(name = "floor_number")
    private Integer floorNumber;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "availability_status")
    private Boolean availabilityStatus;

    @Column(name = "daily_charge")
    private Double dailyCharge;

    // ManyToOne room belongs to one branch
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

}
