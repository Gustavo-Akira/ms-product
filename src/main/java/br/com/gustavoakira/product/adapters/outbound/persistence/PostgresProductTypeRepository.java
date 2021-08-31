package br.com.gustavoakira.product.adapters.outbound.persistence;

import br.com.gustavoakira.product.adapters.outbound.persistence.entities.ProductTypeEntity;
import br.com.gustavoakira.product.application.domain.ProductType;
import br.com.gustavoakira.product.application.ports.ProductTypeRepositoryPort;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;
import java.util.UUID;

@Component
@Primary
public class PostgresProductTypeRepository implements ProductTypeRepositoryPort {

    private final SpringReactiveProductTypeRepository repository;

    public PostgresProductTypeRepository(SpringReactiveProductTypeRepository repository) {
        this.repository = repository;
    }

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Mono<ProductType> save(ProductType type) {
        Mono<ProductTypeEntity> productTypeEntity = repository.save(modelMapper.map(type, ProductTypeEntity.class));
        return productTypeEntity.flatMap(x->{
            ProductType productType = modelMapper.map(x,ProductType.class);
            return Mono.just(productType);
        }).switchIfEmpty(Mono.create(x->{throw new NoSuchElementException();}));
    }

    @Override
    public Flux<ProductType> findAll() {
        Flux<ProductTypeEntity> typeEntityFlux = repository.findAll(Sort.by("name"));
        Flux<ProductType> returnFlux = typeEntityFlux.map(x-> modelMapper.map(x,ProductType.class));
        return returnFlux;
    }

    @Override
    public Mono<ProductType> findById(UUID id) {
        Mono<ProductTypeEntity> productTypeEntity = repository.findById(id);
        return productTypeEntity.flatMap(x-> Mono.just(modelMapper.map(x,ProductType.class))).switchIfEmpty(Mono.create(x->{
            throw new NoSuchElementException("");
        }));
    }

    @Override
    public Mono<ProductType> update(UUID id, ProductType type) {
        Mono<ProductTypeEntity> productTypeEntityMono = repository.findById(id);
        return productTypeEntityMono.flatMap(x->{
            type.setId(id);
            return repository.save(modelMapper.map(type,ProductTypeEntity.class)).map(y->modelMapper.map(y,ProductType.class));
        }).switchIfEmpty(Mono.create(x->{throw new NoSuchElementException();}));
    }

    @Override
    public Mono<Void> delete(UUID id) {
        Mono<ProductTypeEntity> productTypeEntityMono = repository.findById(id);
        return productTypeEntityMono.flatMap(x->{
            return repository.deleteById(id);
        });
    }
}
