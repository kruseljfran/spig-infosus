package com.example.spig_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
public class Utakmica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String lokacija;

    @Column(nullable = false)
    private LocalDateTime datumVrijeme;

    @Column(nullable = false)
    private Integer kapacitet;

    @Column(nullable = false)
    private Integer trenutnoPrijavljenih = 0;

    @ManyToOne
    @JoinColumn(name = "sport_id", nullable = false)
    private Sport sport;
}
