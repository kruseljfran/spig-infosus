package com.example.spig_backend.service;

import com.example.spig_backend.model.Utakmica;
import com.example.spig_backend.repository.UtakmicaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UtakmicaService {
    private final UtakmicaRepository utakmicaRepository;

    public UtakmicaService(UtakmicaRepository utakmicaRepository) {
        this.utakmicaRepository = utakmicaRepository;
    }

    public List<Utakmica> getAllUtakmice() {
        return utakmicaRepository.findAll();
    }

    public Utakmica getUtakmicaById(Long id) {
        return utakmicaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utakmica nije pronađena"));
    }

    public Utakmica createUtakmica(Utakmica utakmica) {
        utakmica.setTrenutnoPrijavljenih(0);
        return utakmicaRepository.save(utakmica);
    }

    public Utakmica updateUtakmica(Long id, Utakmica detalji) {
        Utakmica utakmica = getUtakmicaById(id);
        utakmica.setLokacija(detalji.getLokacija());
        utakmica.setDatumVrijeme(detalji.getDatumVrijeme());
        utakmica.setKapacitet(detalji.getKapacitet());
        utakmica.setSport(detalji.getSport());
        return utakmicaRepository.save(utakmica);
    }

    public void deleteUtakmica(Long id) {
        utakmicaRepository.deleteById(id);
    }
}
