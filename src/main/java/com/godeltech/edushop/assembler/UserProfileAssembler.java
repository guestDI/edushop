package com.godeltech.edushop.assembler;

import com.godeltech.edushop.dto.UserProfileDTO;
import com.godeltech.edushop.model.User;
import org.springframework.stereotype.Component;

import javax.jws.soap.SOAPBinding;

/**
 * Created by d.ihnatovich on 10/10/2017.
 */
@Component
public class UserProfileAssembler {

    public User assemble(User user, UserProfileDTO dto){
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());
        user.setEmail(dto.getEmail());

        return user;
    }
}
