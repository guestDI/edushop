package com.godeltech.edushop;

import com.godeltech.edushop.model.Role;
import com.godeltech.edushop.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Created by d.ihnatovich on 9/29/2017.
 */
@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        Role adminRole = new Role("admin", "Master");
        Role sellerRole = new Role("seller", "Master");
        Role buyerRole = new Role("buyer", "Master");

        roleRepository.save(adminRole);
        roleRepository.save(sellerRole);
        roleRepository.save(buyerRole);

    }

}
