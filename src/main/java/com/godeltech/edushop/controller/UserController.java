package com.godeltech.edushop.controller;

import com.godeltech.edushop.assembler.UserProfileAssembler;
import com.godeltech.edushop.converter.UserConverter;
import com.godeltech.edushop.dto.UserDTO;
import com.godeltech.edushop.dto.UserProfileDTO;
import com.godeltech.edushop.model.Role;
import com.godeltech.edushop.model.User;
import com.godeltech.edushop.repository.RoleRepository;
import com.godeltech.edushop.repository.UserRepository;
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

@Transactional
@CrossOrigin
@RestController
@RequestMapping(value = "api/v1/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UserProfileAssembler userProfileAssembler;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<User> findAll() {

        return userRepository.findAll();
    }

    @RequestMapping(value = "/getNotAdmin", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> findAllNotAdmin() {

        return new ResponseEntity<>(userConverter.convertUser(userRepository.findExceptAdmin()), HttpStatus.OK);
    }

    @RequestMapping(value = "/getUserInfo/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserProfileDTO> getUserInfo(@PathVariable("id") Long id) {

        return new ResponseEntity<>(userConverter.convertUserInfo(userRepository.findOne(id)), HttpStatus.OK);

    }

    @RequestMapping(value = "/addUser", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public User addUser(@RequestBody User user) {

        Role role = roleRepository.findOne(user.getRole().getId());

        User newUser = User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .role(role)
                .build();

        return userRepository.save(newUser);

    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserProfileDTO updateUser(@RequestBody UserProfileDTO userProfileDto) {
//        User user = userRepository.findOne(userProfileDto.getId());
//        return userRepository.save(userProfileAssembler.assemble(user, userProfileDto));
        userRepository.update(userProfileDto);
        return userProfileDto;
    }

    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteUser(@PathVariable("id") Long id) {

        userRepository.delete(id);
    }

}
