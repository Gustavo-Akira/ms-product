package br.com.gustavoakira.product.application.services;

import br.com.gustavoakira.product.application.domain.ProductType;
import br.com.gustavoakira.product.application.ports.ProductTypeRepositoryPort;
import br.com.gustavoakira.product.application.ports.ProductTypeServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class ProductTypeServiceImpl implements ProductTypeServicePort {

    @Autowired
    ProductTypeRepositoryPort repositoryPort;
    @Override
    public Mono<ProductType> save(ProductType type) {
        return repositoryPort.save(type);
    }

    @Override
    public Mono<ProductType> update(UUID id, ProductType type) {
        return repositoryPort.update(id, type);
    }

    @Override
    public Mono<Void> remove(UUID id) {
        return repositoryPort.delete(id);
    }

    @Override
    public Mono<ProductType> getOne(UUID id) {
        return repositoryPort.findById(id);
    }

    @Override
    public Flux<ProductType> getAll() {
        return repositoryPort.findAll();
    }
}
