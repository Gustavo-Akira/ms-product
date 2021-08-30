package br.com.gustavoakira.product.adapters.outbound.persistence;

import br.com.gustavoakira.product.adapters.outbound.persistence.entities.ProductEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
public interface SpringReactivePostgresProductRepository  extends ReactiveCrudRepository<ProductEntity, UUID> {

    @Override
    @Query("select product.*, product_type.id as productId, product_type.name as productName from ProductEntity product join ProductTypeEntity product_type.id = product.type_id")
    Flux<ProductEntity> findAll();
}
