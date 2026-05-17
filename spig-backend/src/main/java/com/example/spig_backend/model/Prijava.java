package com.example.spig_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Data
@Entity
public class Prijava {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String imeIgraca;

    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "utakmica_id", nullable = false)
    @JsonBackReference
    private Utakmica utakmica;

    @ManyToOne
    @JoinColumn(name = "pozicija_id")
    private Pozicija pozicija;
}
