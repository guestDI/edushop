package com.godeltech.edushop.service;

import com.godeltech.edushop.model.Role;
import com.godeltech.edushop.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by d.ihnatovich on 10/11/2017.
 */
@Service
@Transactional
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Iterable<Role> getAll(){
        return roleRepository.findAll();
    }

    public Iterable<Role> getRoleExceptAdmin(){
        return roleRepository.findRolesForNewUsers();
    }
}
