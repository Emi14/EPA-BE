package com.epa.team.dashboard.utils.mapper;

import com.epa.team.dashboard.model.User;
import com.epa.team.dashboard.model.VacationRequest;
import com.epa.team.dashboard.resource.UserResource;
import com.epa.team.dashboard.resource.VacationRequestResource;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class Mapper extends ConfigurableMapper {

    protected void configure(MapperFactory factory) {

        factory.classMap(User.class, UserResource.class)
                .field("id","id")
                .field("username","username")
                .field("password","password")
                .field("role","role")
                .field("daysOff","daysOff")
                .field("workFromHome","workFromHome")
                .register();

        factory.classMap(VacationRequest.class, VacationRequestResource.class)
                .field("id","id")
                .field("startDate","startDate")
                .field("endDate","endDate")
                .field("vacationRequestStatus","vacationRequestStatus")
                .field("vacationRequestType","vacationRequestType")
                .customize(new CustomMapper<VacationRequest, VacationRequestResource>() {
                    @Override
                    public void mapAtoB(VacationRequest vacationRequest, VacationRequestResource vacationRequestResource, MappingContext context) {
                       vacationRequestResource.setUser(factory.getMapperFacade().map(vacationRequest.getUser(), UserResource.class));
                    }

                    @Override
                    public void mapBtoA(VacationRequestResource vacationRequestResource, VacationRequest vacationRequest, MappingContext context) {
                        vacationRequest.setUser(factory.getMapperFacade().map(vacationRequestResource.getUser(), User.class));
                    }
                })
                .register();

    }


}