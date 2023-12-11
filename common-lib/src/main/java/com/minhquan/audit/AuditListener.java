package com.minhquan.audit;

import com.minhquan.model.SqlModel;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class AuditListener {
    @PrePersist
    public void setCreatedAt(Auditable auditable) {
        SqlModel audit = auditable.getAudit();

        if (audit == null) {
            audit = new SqlModel();
            auditable.setAudit(audit);
        }

        audit.setCreatedAt(LocalDateTime.now());
        audit.setCreatedBy("unknown");
    }

    @PreUpdate
    public void setUpdatedAt(Auditable auditable) {
        SqlModel audit = auditable.getAudit();

        audit.setUpdatedAt(LocalDateTime.now());
        audit.setUpdatedBy("unknown");
    }
}
