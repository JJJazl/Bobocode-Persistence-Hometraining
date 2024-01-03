package com.example.persistencehomework.HW22.entity;

import com.example.persistencehomework.HW22.annotation.Id;
import com.example.persistencehomework.HW22.annotation.Table;
import lombok.Data;

@Table(name = "users")
@Data
public class User {

    @Id
    private Long id;

    private String email;

    private String username;

    private int money;
}