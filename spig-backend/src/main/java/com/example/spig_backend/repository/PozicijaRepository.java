package com.example.spig_backend.repository;

import com.example.spig_backend.model.Pozicija;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PozicijaRepository extends JpaRepository<Pozicija, Long> {
}
