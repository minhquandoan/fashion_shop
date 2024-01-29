package com.minhquan.repository;

import java.util.UUID;

import com.minhquan.entity.Product;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductRepository implements PanacheRepositoryBase<Product, UUID> {
}
