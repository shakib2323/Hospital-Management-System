package com.hms.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.hms.audit.Auditable;
import com.hms.enums.BillStatus;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "billing")
public class Billing extends Auditable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billId;

    @Column(name = "bill_number", length = 50)
    private String billNumber;

    @Column(name = "consultation_charges")
    private Double consultationCharges;

    @Column(name = "medicine_charges")
    private Double medicineCharges;

    @Column(name = "lab_charges")
    private Double labCharges;

    @Column(name = "room_charges")
    private Double roomCharges;

    @Column(name = "discount_amount")
    private Double discountAmount;

    @Column(name = "tax_amount")
    private Double taxAmount;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "bill_status", length = 50)
    private BillStatus billStatus;

    @Column(name = "generated_date")
    private LocalDate generatedDate;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}