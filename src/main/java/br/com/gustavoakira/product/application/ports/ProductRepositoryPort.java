package br.com.gustavoakira.product.application.ports;

import br.com.gustavoakira.product.application.domain.Page;
import br.com.gustavoakira.product.application.domain.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.util.UUID;

public interface ProductRepositoryPort {
    Mono<Product> save(Product product);
    Flux<Product> findAll(Page page);
    Mono<Product> findById(UUID productId);
    Mono<Product> update(UUID productId, Product product);
    Mono<Void> delete(UUID productId);
}
