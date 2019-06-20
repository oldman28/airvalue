package ru.rival.airvalue.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Data
@Table(name = "User")
@Accessors(chain = true)
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToOne
    @JoinColumn
    private Role role;
}
