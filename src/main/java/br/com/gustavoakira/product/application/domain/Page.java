package br.com.gustavoakira.product.application.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Page {
    private int pageNumber;
    private int pageSize;
}
