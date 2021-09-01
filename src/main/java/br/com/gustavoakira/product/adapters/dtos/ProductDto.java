package br.com.gustavoakira.product.adapters.dtos;

import br.com.gustavoakira.product.application.domain.ProductType;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class ProductDto {
    @NotEmpty
    @NotNull(message = "name cannot be null")
    private String name;

    @Min(1)
    @Max(50)
    private Integer quantity;

    @NotNull
    @Min(0)
    @Max(2900)
    private Double price;

    @NotNull(message = "id type cannot be null")
    private UUID id_type;

}
