package br.com.gustavoakira.product.application.ports;

import br.com.gustavoakira.product.application.domain.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ProductServicePort {
    Mono<Product> save(Product product);
    Mono<Product> update(UUID id, Product product);
    Mono<Product> getOne(UUID id);
    Flux<Product> getAll();
    Mono<Void> remove(UUID id);
}
