package com.hms.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.hms.audit.Auditable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "pharmacy_stock")
public class PharmacyStock extends Auditable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockId;

    @Column(name = "batch_number", length = 100)   
    private String batchNumber;

    @Column(name = "quantity_available")
    private Integer quantityAvailable;

    @Column(name = "minimum_stock_level")
    private Integer minimumStockLevel;

    @Column(name = "maximum_stock_level")
    private Integer maximumStockLevel;

    @Column(name = "purchase_date")
    private LocalDate purchaseDate;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Column(name = "supplier_name", length = 150)
    private String supplierName;

    @Column(name = "storage_location", length = 150)
    private String storageLocation;

    //ManyToOne one medicine can have multiple stock batches
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;
}
