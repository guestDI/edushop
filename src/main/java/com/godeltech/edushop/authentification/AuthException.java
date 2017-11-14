package com.godeltech.edushop.authentification;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by d.ihnatovich on 11/14/2017.
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "There is no such user")
public class AuthException extends RuntimeException {

    public AuthException(String error) {
        super(error);
    }
}
