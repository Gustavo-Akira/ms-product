package br.com.gustavoakira.product.application.ports;

import br.com.gustavoakira.product.application.domain.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.util.UUID;

public interface ProductRepositoryPort {
    Mono<Product> save(Product product);
    Flux<Product> findAll();
    Mono<Product> findById(UUID productId);
    Mono update(UUID productId, Product product);
    Mono delete(UUID productId);
}
