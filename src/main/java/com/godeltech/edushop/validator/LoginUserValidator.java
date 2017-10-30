package com.godeltech.edushop.validator;

import com.godeltech.edushop.dto.LoginDTO;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by d.ihnatovich on 10/30/2017.
 */
@Component
public class LoginUserValidator {

    public void validate(LoginDTO loginDTO) throws RuntimeException {
        if(StringUtils.isEmpty(loginDTO.getUsername())){
            throw new RuntimeException("Username is empty");
        }

        if(StringUtils.isEmpty(loginDTO.getPassword())){
            throw new RuntimeException("Password is empty");
        }
    }
}
