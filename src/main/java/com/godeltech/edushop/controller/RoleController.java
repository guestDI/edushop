package com.godeltech.edushop.controller;

import com.godeltech.edushop.model.Role;
import com.godeltech.edushop.repository.RoleRepository;
import com.godeltech.edushop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Dmitry on 05.10.2017.
 */

@CrossOrigin
@RestController
@RequestMapping(value = "api/v1/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Role> findAll() {
        return roleService.getAll();
    }

    @RequestMapping(value = "/getNewUserRoles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Role> findNewUserRoles() {
        return roleService.getRoleExceptAdmin();
    }
}
