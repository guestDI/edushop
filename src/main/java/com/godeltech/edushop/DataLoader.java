package com.godeltech.edushop;

import com.godeltech.edushop.model.*;
import com.godeltech.edushop.repository.*;
import com.godeltech.edushop.rest.NetClientGet;
import com.godeltech.edushop.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.soap.SOAPBinding;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

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
    @Autowired
    private RateRepository rateRepository;
    @Autowired
    private NetClientGet netClientGet;
    @Autowired
    private RateService rateService;

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
                .username("sbuyer")
                .password("sbuyer")
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

        Category tshirts = new Category(clothes, "T-shirts", null);
        Category pants = new Category(clothes, "Pants", null);

        categoryRepository.save(phone);
        categoryRepository.save(tv);
        categoryRepository.save(console);
        categoryRepository.save(car);
        categoryRepository.save(tires);
        categoryRepository.save(tshirts);
        categoryRepository.save(pants);

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

        Item item4 = Item.builder()
                .supplier(sellerUser)
                .category(car)
                .manufacturer("Nike")
                .name("Nike Pepe FEELS GOOD MAN")
                .description("T-shirt for cool guys")
                .quantity(11)
                .price(new BigDecimal("16.9"))
                .build();

        Item item5 = Item.builder()
                .supplier(sellerUser)
                .category(car)
                .manufacturer("H&M")
                .name("Slim-fit Pants High waist")
                .description("5-pocket pants in superstretch twill with slim legs and a high waist.")
                .quantity(11)
                .price(new BigDecimal("29.99"))
                .discount(20)
                .build();

        itemRepository.save(item);
        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);
        itemRepository.save(item4);
        itemRepository.save(item5);

//        rateService.saveCurrentRate();
    }

}
