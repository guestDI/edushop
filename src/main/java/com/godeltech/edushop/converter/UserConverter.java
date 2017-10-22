package com.godeltech.edushop.converter;

import com.godeltech.edushop.dto.UserDTO;
import com.godeltech.edushop.dto.UserProfileDTO;
import com.godeltech.edushop.model.User;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by d.ihnatovich on 10/9/2017.
 */
@Component
public class UserConverter {

    public List<UserDTO> convertUser(List<User> users) {
        return users.stream()
                .map((u) -> new UserDTO(u.getId(), u.getUsername(), u.getEmail(), u.getRole(), u.isActive(), u.getRegistrationDate()))
                .collect(Collectors.toList());
    }

    public UserDTO convertUser(User user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getRole(), user.isActive(), user.getRegistrationDate());
    }

    public UserProfileDTO convertUserInfo(User user) {
        return new UserProfileDTO(user.getId(), user.getFirstname(), user.getLastname(), user.getEmail(), user.getRole().getName(), user.getProfilePhoto());

    }
}
