package com.godeltech.edushop.dto;

import com.godeltech.edushop.model.Role;
import lombok.*;

import java.util.Date;

/**
 * Created by d.ihnatovich on 10/9/2017.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLoginDTO {
    private Long id;
    private String username;
    private String role;
    private boolean active;
    private String token;
}
