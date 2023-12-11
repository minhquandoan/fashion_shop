package com.minhquan.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Embeddable
@Getter
@Setter
public class SqlModel {

    private LocalDateTime createdAt;
    private String createdBy;

    private LocalDateTime updatedAt;
    private String updatedBy;
}
