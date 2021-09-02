package br.com.gustavoakira.product.adapters.outbound.persistence;

import br.com.gustavoakira.product.adapters.outbound.persistence.entities.ProductEntity;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface SpringReactivePostgresProductRepository  extends ReactiveCrudRepository<ProductEntity, UUID> {

    @Override
    @Query("select product.*, product_type.id as typeId, product_type.name as typeName from Product product join Product_Type product_type on product_type.id = product.product_type_id")
    Flux<ProductEntity> findAll();

    @Override
    @Query("select product.*, product_type.id as typeId, product_type.name as typeName from Product product join Product_Type product_type on product_type.id = product.product_type_id where product.id=:uuid")
    Mono<ProductEntity> findById(UUID uuid);

    @Query("select count(product.id) as pages, product.*, product_type.id as typeId, product_type.name as typeName from Product product" +
            " join Product_Type product_type on product_type.id = product.product_type_id  GROUP BY product.id,product_type.id " +
            "offset :page limit :limit")
    Flux<ProductEntity> findAll(int page, int limit);
}
