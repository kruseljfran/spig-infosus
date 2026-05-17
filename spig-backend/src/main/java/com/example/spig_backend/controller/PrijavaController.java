package com.example.spig_backend.controller;

import com.example.spig_backend.model.Prijava;
import com.example.spig_backend.service.PrijavaService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/prijave")
@CrossOrigin(origins = "*")
public class PrijavaController {
    private final PrijavaService prijavaService;

    public PrijavaController(PrijavaService prijavaService) {
        this.prijavaService = prijavaService;
    }

    @GetMapping("/utakmica/{utakmicaId}")
    public List<Prijava> getPrijaveZaUtakmicu(@PathVariable Long utakmicaId) {
        return prijavaService.getPrijaveByUtakmica(utakmicaId);
    }

    @PostMapping("/utakmica/{utakmicaId}")
    public Prijava dodajPrijavu(@PathVariable Long utakmicaId, @RequestBody Map<String, String> payload) {
        String imeIgraca = payload.get("imeIgraca");
        Long pozicijaId = payload.get("pozicijaId") != null ? Long.parseLong(payload.get("pozicijaId")) : null;
        return prijavaService.dodajPrijavuNaUtakmicu(utakmicaId, imeIgraca, pozicijaId);
    }

    @PutMapping("/{id}")
    public Prijava azurirajPrijavu(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        String novoIme = payload.get("imeIgraca");
        Long pozicijaId = payload.get("pozicijaId") != null ? Long.parseLong(payload.get("pozicijaId")) : null;
        return prijavaService.updatePrijava(id, novoIme, pozicijaId);
    }

    @DeleteMapping("/{id}")
    public void obrisiPrijavu(@PathVariable Long id) {
        prijavaService.obrisiPrijavu(id);
    }
}
