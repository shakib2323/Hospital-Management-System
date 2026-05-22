package com.hms.audit;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "audit_log")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auditId;

    @Column(name = "username", length = 100)
    private String username;

    @Column(name = "action", length = 50)
    private String action;

    @Column(name = "entity_name", length = 100)
    private String entityName;

    @Column(name = "details", length = 2000)
    private String details;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;
}