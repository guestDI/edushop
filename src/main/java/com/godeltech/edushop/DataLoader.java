package com.godeltech.edushop;

import com.godeltech.edushop.model.Item;
import com.godeltech.edushop.model.Role;
import com.godeltech.edushop.model.User;
import com.godeltech.edushop.repository.ItemRepository;
import com.godeltech.edushop.repository.RoleRepository;
import com.godeltech.edushop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.soap.SOAPBinding;
import java.util.Arrays;

/**
 * Created by d.ihnatovich on 9/29/2017.
 */
@Component
@Transactional(propagation = Propagation.REQUIRED)
public class DataLoader implements ApplicationRunner {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        Role adminRole = new Role("Administrator", "Master and God");
        Role sellerRole = new Role("Seller", "Seller");
        Role buyerRole = new Role("Buyer", "Buyer");
        Role premiumBuyerRole = new Role("Premium_Buyer", "Buyer that have possibility to see list of discount items");

        roleRepository.save(adminRole);
        roleRepository.save(sellerRole);
        roleRepository.save(buyerRole);
        roleRepository.save(premiumBuyerRole);

        User adminUser = User.builder()
                .username("administrator")
                .password("administrator")
                .email("ignatovich.dm@gmail.com")
                .role(adminRole)
                .active(true)
                .build();

        User buyerUser = User.builder()
                .username("buyer")
                .password("buyer")
                .email("test.dm@gmail.com")
                .role(buyerRole)
                .active(true)
                .build();

        User sellerUser = User.builder()
                .username("seller")
                .password("seller")
                .email("test1.dm@gmail.com")
                .role(sellerRole)
                .active(true)
                .build();

        userRepository.save(adminUser);
        userRepository.save(buyerUser);
        userRepository.save(sellerUser);

        Item item = Item.builder()
                .supplier(buyerUser)
                .category("Category")
                .manufacturer("Manufacturer")
                .name("Name")
                .description("Description")
                .quantity(2)
                .price(2.2)
                .build();

        itemRepository.save(item);
    }

}
