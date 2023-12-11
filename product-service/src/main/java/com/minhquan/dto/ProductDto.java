package com.minhquan.dto;

import com.minhquan.entity.ProductEntity;
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
public class ProductDto {
    @Schema(
            name = "Id",
            description = "Unique ID of Product",
            readOnly = true
    )
    private Long id;

    private String name;

    private String description;

    public static ProductEntity toEntity(ProductDto dto) {
        return ProductEntity.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }

    public static ProductDto toDto(ProductEntity entity) {
        return ProductDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }
}
