package br.com.gustavoakira.product.application.services;

import br.com.gustavoakira.product.application.domain.Page;
import br.com.gustavoakira.product.application.domain.Product;
import br.com.gustavoakira.product.application.ports.ProductRepositoryPort;
import br.com.gustavoakira.product.application.ports.ProductServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class ProductService implements ProductServicePort {

    @Autowired
    private ProductRepositoryPort productRepositoryPort;

    @Override
    public Mono<Product> save(Product product) {
        return productRepositoryPort.save(product);
    }

    @Override
    public Flux<Product> getAll(Page page) {
        return productRepositoryPort.findAll(page);
    }

    @Override
    public Mono<Product> getOne(UUID productId) {
        return productRepositoryPort.findById(productId);
    }

    @Override
    public Mono<Product> update(UUID productId, Product product) {
        return productRepositoryPort.update(productId,product);
    }

    @Override
    public Mono<Void> remove(UUID productId) {
        return productRepositoryPort.delete(productId);
    }
}
