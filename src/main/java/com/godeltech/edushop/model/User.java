package com.godeltech.edushop.model;

import javassist.bytecode.ByteArray;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column
    private String firstname;
    @Column
    private String lastname;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = {
            @JoinColumn(name = "user_id", nullable = false) },
            inverseJoinColumns = { @JoinColumn(name = "role_id",
                    nullable = false, updatable = false) })
    private List<Role> roles;
    @Column(nullable = false)
    private boolean active;
    @Column(nullable = false)
    private Date registrationDate;
    private ByteArray profilePhoto;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private List<Item> items;

}
