package br.com.gustavoakira.product.application.ports;

import br.com.gustavoakira.product.application.domain.ProductType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ProductTypeRepositoryPort{
    Mono<ProductType> save(ProductType type);
    Flux<ProductType> findAll();
    Mono<ProductType> findById(UUID id);
    Mono<ProductType> update(UUID id, ProductType type);
    Mono<Void> delete(UUID id);
}
