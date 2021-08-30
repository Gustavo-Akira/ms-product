package br.com.gustavoakira.product.application.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class Product {
    private UUID id;
    private String name;
    private Double price;
    private Integer quantity;
    private ProductType type;
}
