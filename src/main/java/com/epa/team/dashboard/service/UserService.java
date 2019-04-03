package com.epa.team.dashboard.service;

import com.epa.team.dashboard.model.User;
import com.epa.team.dashboard.repository.IUserRepository;
import com.epa.team.dashboard.resource.UserResource;
import com.epa.team.dashboard.utils.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ionut Emanuel Mihailescu on 4/3/19.
 */
@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private Mapper mapper;

    public List<UserResource> getAllUserResources (){
        return mapper.mapAsList(userRepository.findAll(), UserResource.class);
    }

    public UserResource getUserResourceById(Long userId){
        return mapper.map(userRepository.findById(userId), UserResource.class);
    }

    public UserResource saveUser(UserResource userResource) {
        User userToBeSaved = mapper.map(userResource, User.class);
        return mapper.map(userRepository.save(userToBeSaved), UserResource.class);
    }

    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }
}
