package com.minhquan.entity;

import com.minhquan.audit.AuditListener;
import com.minhquan.audit.Auditable;
import com.minhquan.model.SqlModel;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@EntityListeners(AuditListener.class)
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity implements Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private SqlModel audit; // Enhance when use sql data
}
