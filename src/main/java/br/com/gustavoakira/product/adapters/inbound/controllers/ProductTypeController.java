package br.com.gustavoakira.product.adapters.inbound.controllers;

import br.com.gustavoakira.product.adapters.dtos.ProductTypeDto;
import br.com.gustavoakira.product.application.domain.Product;
import br.com.gustavoakira.product.application.domain.ProductType;
import br.com.gustavoakira.product.application.ports.ProductTypeServicePort;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
public class ProductTypeController {

    @Autowired
    ProductTypeServicePort service;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/product-type")
    public Mono<ProductType> saveType(@RequestBody @Valid ProductTypeDto dto){

        return service.save(modelMapper.map(dto,ProductType.class));
    }

    @GetMapping("/product-types")
    public Flux<ProductType> getAll(){
        return service.getAll();
    }
}
