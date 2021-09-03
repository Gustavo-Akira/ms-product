package br.com.gustavoakira.product.adapters.outbound.persistence.entities.converter;

import br.com.gustavoakira.product.adapters.outbound.persistence.entities.ProductEntity;
import br.com.gustavoakira.product.adapters.outbound.persistence.entities.ProductTypeEntity;
import io.r2dbc.spi.Row;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.util.UUID;
@ReadingConverter
public class ProductReadConverter implements Converter<Row, ProductEntity> {

    @Override
    public ProductEntity convert(Row source) {
        ProductTypeEntity productTypeEntity = new ProductTypeEntity();
        productTypeEntity.setId(source.get("typeId", UUID.class));
        productTypeEntity.setName(source.get("typeName",String.class));
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(source.get("id",UUID.class));
        productEntity.setName(source.get("name",String.class));
        productEntity.setQuantity(source.get("quantity",Integer.class));
        productEntity.setPrice(source.get("price",Double.class));
        productEntity.setType(productTypeEntity);
        if (source.toString().contains("pages")){
            productEntity.setPages(source.get("pages",Integer.class));
        }
        return productEntity;
    }

    @Override
    public <U> Converter<Row, U> andThen(Converter<? super ProductEntity, ? extends U> after) {
        return Converter.super.andThen(after);
    }
}
