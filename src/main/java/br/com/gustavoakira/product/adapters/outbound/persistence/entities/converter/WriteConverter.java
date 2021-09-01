package br.com.gustavoakira.product.adapters.outbound.persistence.entities.converter;

import br.com.gustavoakira.product.adapters.outbound.persistence.entities.ProductEntity;
import br.com.gustavoakira.product.application.domain.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.r2dbc.core.Parameter;

@WritingConverter
public class WriteConverter implements Converter<ProductEntity,OutboundRow> {
    @Override
    public OutboundRow convert(ProductEntity source) {
        OutboundRow row = new OutboundRow();
        if(source.getId() != null){
            row.put("id",Parameter.from(source.getId()));
        }
        row.put("name", Parameter.from(source.getName()));
        row.put("price",Parameter.from(source.getPrice()));
        row.put("quantity",Parameter.from(source.getQuantity()));
        row.put("product_type_id", Parameter.from(source.getType().getId()));
        return row;
    }
}
