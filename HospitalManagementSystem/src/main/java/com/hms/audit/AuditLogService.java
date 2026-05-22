package com.hms.audit;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.hms.repository.AuditLogRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuditLogService
{
    private final AuditLogRepository auditLogRepo;
    public void log(String username, String action, String entityName, String details)
    {
        AuditLog log = new AuditLog();
        log.setUsername(username);
        log.setAction(action);
        log.setEntityName(entityName);
        log.setDetails(details);
        log.setTimestamp(LocalDateTime.now());
        auditLogRepo.save(log);
    }
}