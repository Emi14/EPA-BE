package com.epa.team.dashboard.service;

import com.epa.team.dashboard.model.User;
import com.epa.team.dashboard.repository.IUserRepository;
import com.epa.team.dashboard.resource.UserResource;
import com.epa.team.dashboard.resource.VacationRequestResource;
import com.epa.team.dashboard.utils.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Ionut Emanuel Mihailescu on 4/3/19.
 */
@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private Mapper mapper;

    public List<UserResource> getAllUserResources() {
        return userRepository.findAll().stream()
                .map(this::enrichUserResourceWithVacationRequestResources)
                .collect(Collectors.toList());
    }

    public UserResource getUserResourceById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if(userOptional.isPresent()){
            User user = userOptional.get();
            return enrichUserResourceWithVacationRequestResources(user);
        }

        return null;//todo refactor this in order to throw an error

    }

    private UserResource enrichUserResourceWithVacationRequestResources(User user) {
        UserResource userResource = mapper.map(user, UserResource.class);
        userResource.setVacationRequests(mapper.mapAsList(user.getVacationRequests(), VacationRequestResource.class));
        return userResource;
    }

    public UserResource saveUser(UserResource userResource) {
        User userToBeSaved = mapper.map(userResource, User.class);
        return mapper.map(userRepository.save(userToBeSaved), UserResource.class);
    }

    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }
}
