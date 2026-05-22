package com.hms.entity;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.hms.audit.Auditable;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "department")
public class Department extends Auditable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;
    @Column(name="department_name",nullable = false , length = 255)
    private String departmentName;
    @Column(name="department_code",nullable = false , length = 255)
    private String departmentCode;
    @Column(name="description",nullable = false , length = 255)
    private String description;
    @Column(name="floor_number")
    private Integer floorNumber;
    @Column(name="contect_extension",nullable = false , length = 255)
    private String contactExtension;

   
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

}
