package com.minhquan.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.minhquan.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDto {
    @Schema(
            name = "Id",
            description = "Unique ID of Product",
            readOnly = true
    )
    private Long id;

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
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .erpId(entity.getVariant().getErpId())
                .build();
    }
}
