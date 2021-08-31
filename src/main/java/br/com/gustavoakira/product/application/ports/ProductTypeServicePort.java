package br.com.gustavoakira.product.application.ports;

import br.com.gustavoakira.product.application.domain.ProductType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ProductTypeServicePort {
    Mono<ProductType> save(ProductType type);
    Mono<ProductType> update(UUID id, ProductType type);
    Mono<Void> remove(UUID id);
    Mono<ProductType> getOne(UUID id);
    Flux<ProductType> getAll();
}
