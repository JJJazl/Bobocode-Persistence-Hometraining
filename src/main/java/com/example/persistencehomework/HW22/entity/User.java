package com.example.persistencehomework.HW22.entity;

import com.example.persistencehomework.HW22.annotation.Column;
import com.example.persistencehomework.HW22.annotation.Id;
import com.example.persistencehomework.HW22.annotation.Table;
import lombok.Data;

@Table(name = "users")
@Data
public class User {

    @Id
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private int money;
}