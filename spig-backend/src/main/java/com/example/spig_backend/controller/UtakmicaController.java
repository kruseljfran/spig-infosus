package com.example.spig_backend.controller;

import com.example.spig_backend.model.Utakmica;
import com.example.spig_backend.service.UtakmicaService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/utakmice")
@CrossOrigin(origins = "*")
public class UtakmicaController {
    private final UtakmicaService utakmicaService;

    public UtakmicaController(UtakmicaService utakmicaService) {
        this.utakmicaService = utakmicaService;
    }

    @GetMapping
    public List<Utakmica> getAllUtakmice() {
        return utakmicaService.getAllUtakmice();
    }

    @GetMapping("/{id}")
    public Utakmica getUtakmica(@PathVariable Long id) {
        return utakmicaService.getUtakmicaById(id);
    }

    @PostMapping
    public Utakmica createUtakmica(@RequestBody Utakmica utakmica) {
        return utakmicaService.createUtakmica(utakmica);
    }

    @PutMapping("/{id}")
    public Utakmica updateUtakmica(@PathVariable Long id, @RequestBody Utakmica utakmica) {
        return utakmicaService.updateUtakmica(id, utakmica);
    }

    @DeleteMapping("/{id}")
    public void deleteUtakmica(@PathVariable Long id) {
        utakmicaService.deleteUtakmica(id);
    }
}
