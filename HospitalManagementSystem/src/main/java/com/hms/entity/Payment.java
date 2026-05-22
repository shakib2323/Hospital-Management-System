package com.hms.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.hms.audit.Auditable;
import com.hms.enums.PaymentStatus;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "payment")
public class Payment extends Auditable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @Column(name = "payment_reference", length = 100)
    private String paymentReference;

    @Column(name = "payment_method", length = 50)
    private String paymentMethod;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @Column(name = "amount_paid")
    private Double amountPaid;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", length = 50)
    private PaymentStatus paymentStatus;

    @Column(name = "transaction_id", length = 150)
    private String transactionId;

    //OneToOne bill_id is UNIQUE in DB
    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "bill_id", unique = true)
    private Billing billing;
}
