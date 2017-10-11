package com.godeltech.edushop.controller;

import com.godeltech.edushop.assembler.UserProfileAssembler;
import com.godeltech.edushop.converter.UserConverter;
import com.godeltech.edushop.dto.UserDTO;
import com.godeltech.edushop.dto.UserProfileDTO;
import com.godeltech.edushop.model.Role;
import com.godeltech.edushop.model.User;
import com.godeltech.edushop.repository.RoleRepository;
import com.godeltech.edushop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Dmitry on 30.09.2017.
 */

@CrossOrigin
@RestController
@RequestMapping(value = "api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getNotAdmin", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> findAllNotAdmin() {
        return new ResponseEntity<>(userService.getNotAdminUsers(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getUserInfo/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserProfileDTO> getUserInfo(@PathVariable("id") Long id) {

        return new ResponseEntity<>(userService.getUserProfile(id), HttpStatus.OK);

    }

    @RequestMapping(value = "/addUser", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public User addUser(@RequestBody User user) {

        return userService.addUser(user);

    }

    @RequestMapping(value = "/updateUserProfile", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserProfileDTO updateUser(@RequestBody UserProfileDTO userProfileDto) {
        userService.updateUserProfile(userProfileDto);
        return userProfileDto;
    }

    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteUser(@PathVariable("id") Long id) {

        userService.deleteUser(id);
    }

}
