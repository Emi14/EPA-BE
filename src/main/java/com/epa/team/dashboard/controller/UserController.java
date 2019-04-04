package com.epa.team.dashboard.controller;

import com.epa.team.dashboard.resource.UserResource;
import com.epa.team.dashboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Ionut Emanuel Mihailescu on 4/3/19.
 */
@Controller
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<UserResource>> getAllUser() {
        return new ResponseEntity<>(userService.getAllUserResources(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getUser/{userId}", method = RequestMethod.GET)
    public ResponseEntity<UserResource> getUserById(@PathVariable(value = "userId") Long userId) {
        return new ResponseEntity<>(userService.getUserResourceById(userId), HttpStatus.OK);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<UserResource> saveUser(@RequestBody UserResource userResource) {
        return new ResponseEntity<>(userService.saveUser(userResource), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUserById(@PathVariable(value = "userId") Long userId) {
        userService.deleteUserById(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
