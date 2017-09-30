package com.godeltech.edushop;

import com.godeltech.edushop.model.Role;
import com.godeltech.edushop.model.User;
import com.godeltech.edushop.repository.RoleRepository;
import com.godeltech.edushop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * Created by d.ihnatovich on 9/29/2017.
 */
@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        Role adminRole = new Role("admin", "Master and God");
        Role sellerRole = new Role("seller", "Seller");
        Role buyerRole = new Role("buyer", "Buyer");
        Role premiumBuyerRole = new Role("premium_buyer", "Buyer that have possibility to see list of discount items");

        User admin = new User("administrator", "1234567", "", "", "ignatovich.dm@gmail.com", true);

        roleRepository.save(adminRole);
        roleRepository.save(sellerRole);
        roleRepository.save(buyerRole);
        roleRepository.save(premiumBuyerRole);

        userRepository.save(admin);

    }

}
