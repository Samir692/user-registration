package com.finnplay.userregistrationsystem.entity;


import jakarta.persistence.*;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String firstName;

    @Column(nullable=false)
    private String lastName;

    @Column(name = "birthdate")
    private Date birthdate;

    @Column(nullable=false, unique=true)
    private String email;

    @Column(nullable=false)
    private String password;
}
