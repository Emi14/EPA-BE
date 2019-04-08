package com.epa.team.dashboard.utils.mapper;

import com.epa.team.dashboard.model.User;
import com.epa.team.dashboard.resource.UserResource;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class Mapper extends ConfigurableMapper {

    protected void configure(MapperFactory factory) {

        factory.classMap(User.class, UserResource.class)
                .byDefault()
                .register();

    }


}