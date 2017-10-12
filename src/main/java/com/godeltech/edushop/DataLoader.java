package com.godeltech.edushop;

import com.godeltech.edushop.model.Category;
import com.godeltech.edushop.model.Item;
import com.godeltech.edushop.model.Role;
import com.godeltech.edushop.model.User;
import com.godeltech.edushop.repository.CategoryRepository;
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
import java.math.BigDecimal;
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
    @Autowired
    private CategoryRepository categoryRepository;

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

        Category electronicDevice = new Category("Mobile Phones", null);
        Category clothes = new Category("Pants", null);
        Category auto = new Category("Car", null);
        Category food = new Category("Butter", null);

        categoryRepository.save(electronicDevice);
        categoryRepository.save(clothes);
        categoryRepository.save(auto);
        categoryRepository.save(food);

        Item item = Item.builder()
                .supplier(buyerUser)
                .category(electronicDevice)
                .manufacturer("Samsung")
                .name("S8")
                .description("Phone for rich guys")
                .quantity(2)
                .price(new BigDecimal("1000"))
                .build();

        Item item1 = Item.builder()
                .supplier(buyerUser)
                .category(auto)
                .manufacturer("BMW")
                .name("X5")
                .description("Car for rich guys")
                .quantity(10)
                .price(new BigDecimal("60000.1"))
                .build();

        Item item2 = Item.builder()
                .supplier(buyerUser)
                .category(food)
                .manufacturer("Manufacturer1")
                .name("Rama")
                .description("Good butter")
                .quantity(5)
                .price(new BigDecimal("2.1"))
                .build();

        itemRepository.save(item);
        itemRepository.save(item1);
        itemRepository.save(item2);
    }

}
