package com.godeltech.edushop.service;

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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.plugin.util.UserProfile;

import java.util.List;

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

    public void updateUserProfile(UserProfileDTO userProfileDTO){
        userRepository.update(userProfileDTO);
    }

    public User addUser(User user){
        user.setRole(roleRepository.findOne(user.getRole().getId()));
        return userRepository.save(user);
    }

    public void deleteUser(Long id){
        userRepository.delete(id);
    }

    public List<UserDTO> getNotAdminUsers(){
        return userConverter.convertUser(userRepository.findExceptAdmin());
    }

    public UserProfileDTO getUserProfile(Long id){
        return userConverter.convertUserInfo(userRepository.findOne(id));
    }

}
