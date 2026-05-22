package com.hms.entity;

import com.hms.audit.Auditable;
import com.hms.enums.PermissionType;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name="permissions")
@Data
public class Permission extends Auditable
{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long permissionId;

    @Enumerated(EnumType.STRING)
    private PermissionType permissionName;

}