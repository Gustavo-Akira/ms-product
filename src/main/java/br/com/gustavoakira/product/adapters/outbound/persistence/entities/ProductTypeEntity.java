package br.com.gustavoakira.product.adapters.outbound.persistence.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.util.UUID;

@Data
@Table("product_type")
public class ProductTypeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private UUID id;

    private String name;
}
