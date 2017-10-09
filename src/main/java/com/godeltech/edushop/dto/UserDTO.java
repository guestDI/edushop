package com.godeltech.edushop.dto;

import com.godeltech.edushop.model.Role;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by d.ihnatovich on 10/9/2017.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private Role role;
    private boolean active;
    private Date registrationDate;
}
