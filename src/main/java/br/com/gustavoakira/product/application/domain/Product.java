package br.com.gustavoakira.product.application.domain;

import br.com.gustavoakira.product.adapters.dtos.ProductDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class Product {
    private UUID id;
    private String name;
    private Double price;
    private Integer quantity;
    private ProductType type;
    private int pages=0;

    public Product(ProductDto dto){
        this.price = dto.getPrice();
        this.name = dto.getName();
        this.quantity = dto.getQuantity();
    }
}
