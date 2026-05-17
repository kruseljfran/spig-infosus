package com.example.spig_backend.repository;

import com.example.spig_backend.model.Prijava;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PrijavaRepository extends JpaRepository<Prijava, Long> {
    List<Prijava> findByUtakmicaId(Long utakmicaId);
}
