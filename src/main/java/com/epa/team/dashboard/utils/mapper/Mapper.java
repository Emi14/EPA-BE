package com.epa.team.dashboard.utils.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Mapper extends ConfigurableMapper {

    @PostConstruct
    public void init() {
        final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();


    }

}