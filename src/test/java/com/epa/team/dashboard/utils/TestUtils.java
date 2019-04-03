package com.epa.team.dashboard.utils;

import com.epa.team.dashboard.resource.UserResource;
import com.epa.team.dashboard.resource.UserRole;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Ionut Emanuel Mihailescu on 4/3/19.
 */
public class TestUtils {

    public static List<UserResource> getUsers(){
        return Arrays.asList(new UserResource(1L, "User1", "MASKED", UserRole.USER, 0 ,false, null),
                new UserResource(2L, "User2", "MASKED", UserRole.ADMIN, 24 ,true, null));
    }

    public static UserResource getUserResourceById(Long userId) {
        return new UserResource(userId, "User1", "MASKED", UserRole.USER, 0 ,false, null);
    }
}
