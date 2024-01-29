package com.minhquan.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.minhquan.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class ProductDto {
    @Schema(
            name = "Id",
            description = "Unique ID of Product",
            readOnly = true
    )
    private String id;

    private String name;

    private String description;

    @Schema(
            name = "erpId",
            description = "Variant ID"
    )
    private Long erpId;

    public static Product toEntity(ProductDto dto) {
        return Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }

    public static ProductDto toDto(Product entity) {
        return ProductDto.builder()
                .id(String.valueOf(entity.getId()))
                .name(entity.getName())
                .description(entity.getDescription())
                // .erpId(entity.getVariant().getErpId())
                .build();
    }

    public static Product updateEntity(ProductDto dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());

        return entity;
    }
}
