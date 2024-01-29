package com.minhquan.entity;

import java.util.UUID;

import com.minhquan.audit.AuditListener;
import com.minhquan.audit.Auditable;
import com.minhquan.model.SqlModel;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Product implements Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private String description;

    private SqlModel audit;

    @ManyToOne
    @JoinColumn(name = "erp_id", referencedColumnName = "erp_id", foreignKey = @ForeignKey(name = "erp_id_fk"))
    private Variant variant;
}
