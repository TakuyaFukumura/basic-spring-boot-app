package com.example.myapplication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "message")
@Data
public class Message {

    @Id
    private Long id;

    private String text;
}
