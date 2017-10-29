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
        //Save roles
        Role adminRole = new Role("Administrator", "Master and God of this application");
        Role sellerRole = new Role("Seller", "Seller can sell goods");
        Role buyerRole = new Role("Buyer", "Buyer can buy goods");
        Role premiumBuyerRole = new Role("Premium_Buyer", "Buyer that have possibility to see additional discounts");

        roleRepository.save(adminRole);
        roleRepository.save(sellerRole);
        roleRepository.save(buyerRole);
        roleRepository.save(premiumBuyerRole);

        //Save users
        User adminUser = User.builder()
                .username("administrator")
                .password("administrator")
                .email("admin@gmail.com")
                .role(adminRole)
                .active(true)
                .build();

        User buyerUser = User.builder()
                .username("buyer")
                .password("buyer")
                .email("buyer@gmail.com")
                .role(buyerRole)
                .active(true)
                .build();

        User sellerUser = User.builder()
                .username("seller")
                .password("seller")
                .email("seller@gmail.com")
                .role(sellerRole)
                .active(true)
                .build();

        User disabledSellerUser = User.builder()
                .username("dis_seller")
                .password("seller")
                .email("dis_seller@gmail.com")
                .role(sellerRole)
                .active(false)
                .build();

        userRepository.save(adminUser);
        userRepository.save(buyerUser);
        userRepository.save(sellerUser);
        userRepository.save(disabledSellerUser);

        //Save parent categories
        Category electronicDevice = new Category("Electronic", "Electronic devices");
        Category clothes = new Category("Clothes", null);
        Category auto = new Category("Auto", null);

        categoryRepository.save(electronicDevice);
        categoryRepository.save(clothes);
        categoryRepository.save(auto);

        //Save child categories
        Category phone = new Category(electronicDevice, "Mobile phones", null);
        Category tv = new Category(electronicDevice, "TVs", null);
        Category console = new Category(electronicDevice, "Game Consoles", "Game consoles, accessories and games");

        Category car = new Category(auto, "Cars", null);
        Category tires = new Category(auto, "Tires", null);

        categoryRepository.save(phone);
        categoryRepository.save(tv);
        categoryRepository.save(console);
        categoryRepository.save(car);
        categoryRepository.save(tires);

        //Save items
        Item item = Item.builder()
                .supplier(sellerUser)
                .category(phone)
                .manufacturer("Samsung")
                .name("S8")
                .description("Phone for rich guys")
                .quantity(2)
                .price(new BigDecimal("1000"))
                .build();

        Item item1 = Item.builder()
                .supplier(sellerUser)
                .category(car)
                .manufacturer("BMW")
                .name("X5")
                .description("Car for rich guys")
                .quantity(10)
                .price(new BigDecimal("60000.1"))
                .build();

        Item item2 = Item.builder()
                .supplier(sellerUser)
                .category(tv)
                .manufacturer("Samsung")
                .name("QN65Q9FAMFXZA")
                .description("\"65\" Class Q9F QLED 4K TV")
                .quantity(10)
                .price(new BigDecimal("3300.1"))
                .build();

        Item item3 = Item.builder()
                .supplier(sellerUser)
                .category(car)
                .manufacturer("Opel")
                .name("Astra")
                .description("Car for not very rich guys")
                .quantity(5)
                .price(new BigDecimal("20000.1"))
                .discount(10)
                .build();

        itemRepository.save(item);
        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);
    }

}
