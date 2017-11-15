package com.godeltech.edushop.authentification;

import lombok.Getter;

/**
 * Created by d.ihnatovich on 11/15/2017.
 */
@Getter
public enum RoleEnum {
    PREMIUM_BUYER("Premium_Buyer");

    private String roleName;

    RoleEnum(String roleName) {
        this.roleName = roleName;
    }
}
