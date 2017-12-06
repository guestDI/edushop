package com.godeltech.edushop.controller;

import com.godeltech.edushop.annotation.Permissions;
import com.godeltech.edushop.assembler.UserProfileAssembler;
import com.godeltech.edushop.authentification.LoginInterceptor;
import com.godeltech.edushop.converter.UserConverter;
import com.godeltech.edushop.dto.LoginDTO;
import com.godeltech.edushop.dto.UserDTO;
import com.godeltech.edushop.dto.UserLoginDTO;
import com.godeltech.edushop.dto.UserProfileDTO;
import com.godeltech.edushop.model.Role;
import com.godeltech.edushop.model.User;
import com.godeltech.edushop.repository.RoleRepository;
import com.godeltech.edushop.service.UserService;
import com.godeltech.edushop.validator.LoginUserValidator;
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

    @Autowired
    private LoginUserValidator loginUserValidator;

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Permissions(roles = {"Administrator"})
    @RequestMapping(value = "/getNotAdmin", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> findAllNotAdmin() {
        return new ResponseEntity<>(userService.getNotAdminUsers(), HttpStatus.OK);
    }

    @Permissions(roles = {"Administrator"})
    @RequestMapping(value = "/getDisabledNotAdmin", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> findAllDisabledNotAdmin() {
        return new ResponseEntity<>(userService.getDisabledNotAdminUsers(), HttpStatus.OK);
    }

    @Permissions(roles = {"Administrator"})
    @RequestMapping(value = "/getCountNotAdmin", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public int findNumberOfUsersNotAdmin() {
        return userService.getNumberOfNotAdminUsers();
    }

    @Permissions(roles = {"Administrator"})
    @RequestMapping(value = "/getCountDisabledNotAdmin", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public int findNumberOfDisabledNotAdminUsers() {
        return userService.getNumberOfDisabledNotAdminUsers();
    }

    @Permissions(roles = {"Administrator"})
    @RequestMapping(value = "/getCountNotAdminWithoutItems", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public int findNumberOfdNotAdminUsersWithoutItems() {
        return userService.getNumberOfNotAdminUsersWithoutItems();
    }

    @Permissions(roles = {"Administrator", "Seller", "Buyer", "Premium_Buyer"})
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

    @RequestMapping(value = "/updateUserStatus/{id}/{status}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer updateUserStatus(@PathVariable("id") Long id, @PathVariable("status") boolean status) {
        return userService.updateUserStatus(id, status);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteUser(@PathVariable("id") Long id) {

        userService.deleteUser(id);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserLoginDTO loginUser(@RequestBody LoginDTO loginDTO) {
        loginUserValidator.validate(loginDTO);
        UserLoginDTO userLoginDTO = userService.login(loginDTO.getUsername(), loginDTO.getPassword());
        if (userLoginDTO == null) {
            throw new RuntimeException("Not found");
        } else if(!userLoginDTO.isActive()){
            throw new RuntimeException("User is disabled");
        }
        loginInterceptor.register(userLoginDTO.getId(), userLoginDTO.getToken());

        return userLoginDTO;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void logout(@RequestParam("id") Long id) {
        userService.logout(id);
    }

}
