package com.godeltech.edushop.dto;

import com.godeltech.edushop.model.Role;
import lombok.*;

/**
 * Created by d.ihnatovich on 10/9/2017.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private Role role;
    private byte[] profilePhoto;
}
