package com.hms.audit;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuditListener {

    private static AuditLogService auditLogService;

    @Autowired
    public void setAuditLogService(AuditLogService service) {
        AuditListener.auditLogService = service; //Spring injects once, stored statically
    }

    @PrePersist
    public void prePersist(Object entity) {
        if (auditLogService != null)
            auditLogService.log("system", AuditActionType.CREATE.name(),
                entity.getClass().getSimpleName(), "Created: " + entity.getClass().getSimpleName());
    }

    @PreUpdate
    public void preUpdate(Object entity) {
        if (auditLogService != null)
            auditLogService.log("system", AuditActionType.UPDATE.name(),
                entity.getClass().getSimpleName(), "Updated: " + entity.getClass().getSimpleName());
    }

    @PreRemove
    public void preRemove(Object entity) {
        if (auditLogService != null)
            auditLogService.log("system", AuditActionType.DELETE.name(),
                entity.getClass().getSimpleName(), "Deleted: " + entity.getClass().getSimpleName());
    }
}