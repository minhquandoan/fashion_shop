package com.minhquan.service;

import java.util.List;
import java.util.UUID;

import com.minhquan.constant.exception.ErrorCode;
import com.minhquan.dto.ProductDto;
import com.minhquan.entity.Product;
import com.minhquan.entity.Variant;
import com.minhquan.exception.CommonException;
import com.minhquan.exception.ProductNotFoundException;
import com.minhquan.repository.ProductRepository;

import io.quarkus.logging.Log;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
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
        Log.infov("Start Inserting product {0} ", productDto);
        final var entity = ProductDto.toEntity(productDto);

        if (productDto.getErpId() != null) {
            entity.setVariant(entityManager.getReference(Variant.class, productDto.getErpId()));
        }
    
        this.repository.persist(entity);
        Log.infov("Persisted Product Successfully!! {0}", entity);
        return ProductDto.toDto(entity);
    }

    public List<ProductDto> getProductList(int pageIndex, int pageSize) {
        final var records = this.repository.findAll(Sort.descending("updated_at", "created_at")).page(Page.of(pageIndex, pageSize)).list();
        return records.stream()
                .map(ProductDto::toDto)
                .toList();
    }

    public List<ProductDto> getAllProductList() {
        return this.repository.findAll(Sort.descending("updated_at", "created_at")).stream()
                    .map(ProductDto::toDto).toList();
    }

    public ProductDto getProduct(String id) {
        final var product = this.repository.findById(UUID.fromString(id));
        if (product == null) {
            Log.errorv("Could not find any product with id {0}", id);
            throw new ProductNotFoundException();
        }

        return ProductDto.toDto(product);
    }
    
    @Transactional
    public ProductDto updateProduct(String id, ProductDto newRecord) {
        final var entity = this.repository.findById(UUID.fromString(id));
        if (entity == null) {
            throw new ProductNotFoundException();
        }

        // Map new record to the old one
        Product updatedProduct;
        try {
            Log.tracev("Updating product entity: {0} -> {1} \n", newRecord, entity);
            updatedProduct = ProductDto.updateEntity(newRecord, entity);
        } catch (Exception exception) {
            Log.errorv("Could not update new product entity {0}", newRecord);
            throw new CommonException(ErrorCode.PRODUCT_UPDATE_FAILED, "NotMatchedData");
        }

        try {
            Log.trace("Saving new data...");
            this.repository.persistAndFlush(updatedProduct);
            Log.trace("Saved new data successfully");
        } catch (Exception exception) {
            throw new CommonException(ErrorCode.PRODUCT_UPDATE_FAILED, "DataSavedFailed");
        }

        return ProductDto.toDto(updatedProduct);
    }
}
