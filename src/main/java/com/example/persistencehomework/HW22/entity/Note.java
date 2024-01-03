package com.example.persistencehomework.HW22.entity;

import com.example.persistencehomework.HW22.annotation.Column;
import com.example.persistencehomework.HW22.annotation.Id;
import com.example.persistencehomework.HW22.annotation.Table;
import lombok.Data;

@Table(name = "notes")
@Data
public class Note {

    @Id
    private Long id;

    private String title;

    private String body;

    @Column(name = "user_id")
    private Long userId;
}
