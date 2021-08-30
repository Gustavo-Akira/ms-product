package br.com.gustavoakira.product.adapters.outbound.persistence;

import br.com.gustavoakira.product.adapters.outbound.persistence.entities.ProductTypeEntity;
import br.com.gustavoakira.product.application.domain.ProductType;
import br.com.gustavoakira.product.application.ports.ProductTypeRepositoryPort;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.stream.Stream;

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
        });
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
        return productTypeEntity.flatMap(x-> Mono.just(modelMapper.map(x,ProductType.class)));
    }

    @Override
    public Mono update(UUID id, ProductType type) {
        Mono<ProductTypeEntity> productTypeEntityMono = repository.findById(id);
        if(productTypeEntityMono.blockOptional().isEmpty()){
            type.setId(id);
            repository.save(modelMapper.map(type,ProductTypeEntity.class));
        }
        return Mono.empty();
    }

    @Override
    public Mono delete(UUID id) {
        Mono<ProductTypeEntity> productTypeEntityMono = repository.findById(id);
        if(productTypeEntityMono.blockOptional().isEmpty()){
            repository.deleteById(id);
        }
        return Mono.empty();
    }
}
