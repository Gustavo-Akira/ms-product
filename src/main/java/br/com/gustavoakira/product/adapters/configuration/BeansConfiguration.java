package br.com.gustavoakira.product.adapters.configuration;

import br.com.gustavoakira.product.ProductApplication;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = ProductApplication.class)
public class BeansConfiguration {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
