package org.palad.fakeshop.config;

import lombok.extern.log4j.Log4j2;
import org.modelmapper.*;
import org.modelmapper.convention.MatchingStrategies;
import org.palad.fakeshop.domain.cart.Cart;
import org.palad.fakeshop.domain.cart.Products;
import org.palad.fakeshop.domain.product.Product;
import org.palad.fakeshop.dto.cart.CartDTO;
import org.palad.fakeshop.dto.cart.ProductsDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
@Log4j2
public class RootConfig {

    @Bean
    public ModelMapper getMapper() {

        Provider<LocalDate> localDateProvider = new AbstractProvider<LocalDate>() {
            @Override
            protected LocalDate get() {
                return LocalDate.now();
            }
        };

        Converter<String, LocalDate> toStringDate = new AbstractConverter<String, LocalDate>() {
            @Override
            protected LocalDate convert(String source) {
                DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(source, format);
                return localDate;
            }
        };

        ModelMapper modelMapper = new ModelMapper();

        // Cart.User, CartDTO.Long 간 타입 매핑
        modelMapper.addMappings(
                new PropertyMap<Cart, CartDTO>() {
                    @Override
                    protected void configure() {
                        map().setUserid(source.getUser().getUid());
                    }
                }
        );

        // Products.Product, ProductsDTO.Long 간 타입 매핑
        modelMapper.addMappings(

                new PropertyMap<Products, ProductsDTO>() {

                    @Override
                    protected void configure() {
                        map().setProductid(source.getProductid().getPid());
                    }
                }

        );


        modelMapper.createTypeMap(String.class, LocalDate.class);
        modelMapper.addConverter(toStringDate);


        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.LOOSE);

        return modelMapper;


    }


}
