package org.palad.fakeshop.config;

import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.palad.fakeshop.domain.cart.Cart;
import org.palad.fakeshop.dto.cart.CartDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log4j2
public class RootConfig {

    @Bean
    public ModelMapper getMapper() {

        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(
                new PropertyMap<Cart, CartDTO>() {
                    @Override
                    protected void configure() {
                        map().setUserid(source.getUser().getUid());
                    }
                }
        );

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.LOOSE);

        return modelMapper;


    }


}
