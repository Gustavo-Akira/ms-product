package br.com.gustavoakira.product.adapters.dtos;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ProductTypeDto {
    @NotNull
    @NotEmpty
    private String name;
}
