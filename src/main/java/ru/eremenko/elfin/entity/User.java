package ru.eremenko.elfin.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * @author eremenko
 */
@Entity
@Table(name = "`user`")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;
}
