package com.godeltech.edushop.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javassist.bytecode.ByteArray;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"user\"")
@Builder
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(length = 21)
    private String firstname;
    @Column(length = 21)
    private String lastname;
    @Column(nullable = false)
    private String email;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    @JoinColumn(name="role_id")
    private Role role;
    @Column(nullable = false)
    private boolean active;
    @Column(nullable = false)
    private Date registrationDate = new Date();
    @Column
    private String profilePhoto;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Item> items;

    @PrePersist
    public void prePersist() {
        this.registrationDate = new Date();
        this.active = true;
    }

    public User(String username, String lastname) {
        this.username = username;
        this.lastname = lastname;
    }
}
