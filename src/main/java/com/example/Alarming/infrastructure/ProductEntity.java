package com.example.Alarming.infrastructure;

import com.example.Alarming.domain.Product;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "product")
@Getter
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "restock_round", nullable = false)
    private Integer restockRound;

    @Column(name = "stock_quanity", nullable = false)
    private Integer stockQuantity;

    public static ProductEntity from(Product product) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.id = product.getId();
        productEntity.restockRound = product.getRestockRound();
        productEntity.stockQuantity = product.getStockQuantity();
        return productEntity;
    }

    public Product toModel() {
        return Product.builder()
                .id(id)
                .restockRound(restockRound)
                .stockQuantity(stockQuantity)
                .build();
    }
}
