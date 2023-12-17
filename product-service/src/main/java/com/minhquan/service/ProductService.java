package com.minhquan.service;

import com.minhquan.dto.ProductDto;
import com.minhquan.entity.Variant;
import com.minhquan.repository.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository repository;

    @Inject
    EntityManager entityManager;

    @Transactional
    public ProductDto createProduct(ProductDto productDto) {
        final var entity = ProductDto.toEntity(productDto);
        entity.setVariant(entityManager.getReference(Variant.class, productDto.getErpId()));
        this.repository.persist(entity);

        return ProductDto.toDto(entity);
    }
}
