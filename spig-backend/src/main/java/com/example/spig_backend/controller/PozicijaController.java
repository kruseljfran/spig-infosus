package com.example.spig_backend.controller;

import com.example.spig_backend.model.Pozicija;
import com.example.spig_backend.repository.PozicijaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pozicije")
@CrossOrigin(origins = "http://localhost:5173")
public class PozicijaController {

    private final PozicijaRepository pozicijaRepository;

    public PozicijaController(PozicijaRepository pozicijaRepository) {
        this.pozicijaRepository = pozicijaRepository;
    }

    @GetMapping
    public List<Pozicija> getPozicije() {
        return pozicijaRepository.findAll();
    }
}
