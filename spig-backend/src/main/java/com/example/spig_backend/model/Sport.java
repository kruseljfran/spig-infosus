package com.example.spig_backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Sport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String naziv;
}
