package br.com.gustavoakira.product.application.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductType {
    private UUID id;
    private String name;
}
