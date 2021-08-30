package br.com.gustavoakira.product.adapters.outbound.persistence.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.util.UUID;

@Data
@Table("product")
public class ProductEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private UUID id;

    private String name;

    private Double price;

    private Integer quantity;

    private ProductTypeEntity typeEntity;
}
