package com.minhquan.entity;

import com.minhquan.model.Image;
import com.minhquan.model.SqlModel;
import jakarta.json.Json;
import lombok.Data;

@Data
public class ProductEntity {
    private Long id;

    private String name;

    private String description;

    private Image[] images;

    private Json properties;

    private Variant[] variants;

    private SqlModel model; // Enhance when use sql data
}
