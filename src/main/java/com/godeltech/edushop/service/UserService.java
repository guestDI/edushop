package com.godeltech.edushop.service;

import com.godeltech.edushop.assembler.UserProfileAssembler;
import com.godeltech.edushop.authentification.LoginInterceptor;
import com.godeltech.edushop.converter.UserConverter;
import com.godeltech.edushop.dto.UserDTO;
import com.godeltech.edushop.dto.UserLoginDTO;
import com.godeltech.edushop.dto.UserProfileDTO;
import com.godeltech.edushop.model.Role;
import com.godeltech.edushop.model.User;
import com.godeltech.edushop.repository.RoleRepository;
import com.godeltech.edushop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.plugin.util.UserProfile;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.nonNull;

/**
 * Created by d.ihnatovich on 10/11/2017.
 */
@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UserProfileAssembler userProfileAssembler;

    @Autowired
    private LoginInterceptor loginInterceptor;

    public void updateUserProfile(UserProfileDTO userProfileDTO){
        userRepository.update(userProfileDTO);
    }

    public User addUser(User user){
        if(userRepository.checkUserExists(user.getUsername()) > 0){
            throw new RuntimeException("Username already exists");
        }
        user.setRole(roleRepository.findOne(user.getRole().getId()));
        return userRepository.save(user);
    }

    public void deleteUser(Long id){
        userRepository.delete(id);
    }

    public List<UserDTO> getNotAdminUsers(){
        return userConverter.convertUser(userRepository.findExceptAdmin());
    }

    public Integer getNumberOfNotAdminUsers(){
        return userRepository.findNumberOfUsersExceptAdmin();
    }

    public Integer getNumberOfDisabledNotAdminUsers(){
        return userRepository.findNumberOfDisabledUsersExceptAdmin();
    }

    public Integer getNumberOfNotAdminUsersWithoutItems(){
        return userRepository.findNumberOfUsersWithoutItems();
    }

    public Integer updateUserStatus(Long id, boolean status){
        return userRepository.updateUserStatus(id, status);
    }

    public UserProfileDTO getUserProfile(Long id){
        return userConverter.convertUserInfo(userRepository.findOne(id));
    }

    public UserLoginDTO login(String username, String password) {
        User user = userRepository.login(username, password);
        UserLoginDTO userLoginDTO = null;
        if (nonNull(user)) {
            userLoginDTO = userConverter.convertUserLogin(user);
        }

        return  userLoginDTO;
    }

    public void logout(Long id) {
        loginInterceptor.logout(id);
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findOne(id);
        UserDTO userDTO = null;
        if (user != null) {
            userDTO = userConverter.convertUser(user);
        }

        return userDTO;
    }
}
