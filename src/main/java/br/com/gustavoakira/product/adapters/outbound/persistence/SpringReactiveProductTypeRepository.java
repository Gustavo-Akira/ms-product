package br.com.gustavoakira.product.adapters.outbound.persistence;

import br.com.gustavoakira.product.adapters.outbound.persistence.entities.ProductTypeEntity;
import br.com.gustavoakira.product.application.domain.ProductType;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface SpringReactiveProductTypeRepository extends ReactiveSortingRepository<ProductTypeEntity,UUID> {
}
