package com.godeltech.edushop.model;

/**
 * Created by Dmitry on 05.10.2017.
 */
public enum RolesMap {
    ADMIN("administrator"),
    SELLER("Seller"),
    BUYER("Buyer"),
    PREMIUM_BUYER("Premium_Buyer");

    private String role;

    RolesMap(String role) {
        this.role = role;
    }

    public String role() {
        return role;
    }
}
