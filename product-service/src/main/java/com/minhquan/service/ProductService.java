package com.minhquan.service;

import com.minhquan.dto.ProductDto;
import com.minhquan.repository.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository repository;

    @Transactional
    public ProductDto createProduct(ProductDto productDto) {
        final var entity = ProductDto.toEntity(productDto);
        this.repository.persist(entity);

        return ProductDto.toDto(entity);
    }
}
