package com.example.spig_backend.repository;

import com.example.spig_backend.model.Utakmica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtakmicaRepository extends JpaRepository<Utakmica, Long> {
}
