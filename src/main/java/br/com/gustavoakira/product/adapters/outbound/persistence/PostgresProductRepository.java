package br.com.gustavoakira.product.adapters.outbound.persistence;

import br.com.gustavoakira.product.adapters.outbound.persistence.entities.ProductEntity;
import br.com.gustavoakira.product.application.domain.Product;
import br.com.gustavoakira.product.application.ports.ProductRepositoryPort;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.util.Optional;
import java.util.UUID;


@Component
@Primary
public class PostgresProductRepository implements ProductRepositoryPort {
    private final SpringReactivePostgresProductRepository repository;

    public PostgresProductRepository(SpringReactivePostgresProductRepository productRepository){
        this.repository = productRepository;
    }

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Mono<Product> save(Product product) {
        Mono<ProductEntity> monoProductEntity = repository.save(modelMapper.map(product,ProductEntity.class));
        return monoProductEntity.map(x->modelMapper.map(x,Product.class));
    }

    @Override
    public Flux<Product> findAll() {
        Flux<ProductEntity> productsEntities = repository.findAll();
        return productsEntities.map(x->modelMapper.map(x,Product.class));
    }

    @Override
    public Mono<Product> findById(UUID productId) {
        Mono<ProductEntity> entity = repository.findById(productId);
        return entity.map(x->modelMapper.map(x,Product.class));
    }

    @Override
    public Mono update(UUID productId, Product product) {
        Mono<ProductEntity> productEntityMono = repository.findById(productId);
        productEntityMono.doOnNext(x->{
            product.setId(x.getId());
            repository.save(modelMapper.map(product,ProductEntity.class));
        });
        return Mono.empty();
    }

    @Override
    public Mono delete(UUID productId) {
        Mono<ProductEntity> productEntityMono = repository.findById(productId);
        Optional<ProductEntity> productEntity = productEntityMono.blockOptional();
        if(!productEntity.isEmpty()){
            repository.deleteById(productId);
        }
        return Mono.empty();
    }
}
