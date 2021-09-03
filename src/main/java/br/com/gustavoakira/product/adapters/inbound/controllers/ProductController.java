package br.com.gustavoakira.product.adapters.inbound.controllers;

import br.com.gustavoakira.product.adapters.dtos.ProductDto;
import br.com.gustavoakira.product.application.domain.Page;
import br.com.gustavoakira.product.application.domain.Product;
import br.com.gustavoakira.product.application.domain.ProductType;
import br.com.gustavoakira.product.application.ports.ProductServicePort;
import br.com.gustavoakira.product.application.ports.ProductTypeServicePort;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.UUID;

@RestController
public class ProductController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProductServicePort productServicePort;

    @Autowired
    private ProductTypeServicePort productTypeServicePort;

    @PostMapping("/product")
    public Mono<Product> productMono(@RequestBody @Valid ProductDto productDto){
        return productTypeServicePort.getOne(productDto.getId_type())
                .flatMap(x->{
                    Product product = new Product(productDto);
                    product.setType(x);
                    return productServicePort.save(product);
                });
    }
    @GetMapping("/products/{page}/{limit}")
    public Flux<Product> getProducts(@PathVariable("page") Integer page, @PathVariable("limit") Integer limit){
        return productServicePort.getAll(new Page(page,limit));
    }

    @GetMapping("/product/{id}")
    public Mono<Product> getProduct(@PathVariable UUID id){
        return productServicePort.getOne(id);
    }

    @PutMapping("/product/{id}")
    public Mono<Product> updateProduct(@PathVariable UUID id, @RequestBody ProductDto dto){
        return productTypeServicePort.getOne(dto.getId_type())
                .flatMap(x->{
                    Product product = new Product(dto);
                    product.setType(x);
                    return productServicePort.update(id,product);
                });
    }

    @PatchMapping("/product/{id}/add/{quantity}")
    public Mono<Product> addProduct(@PathVariable("quantity") Integer quantity, @PathVariable("id") UUID id){
        return  productServicePort.addProducts(id,quantity);
    }

    @PatchMapping("/product/{id}/minus/{quantity}")
    public Mono<Product> retireProduct(@PathVariable("quantity") Integer quantity, @PathVariable("id") UUID id){
        return  productServicePort.retireProducts(id,quantity);
    }

    @DeleteMapping("/product/{id}")
    public Mono<Void> removeProduct(@PathVariable UUID id){
        return productServicePort.remove(id);
    }
}
