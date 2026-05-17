package com.example.spig_backend.service;

import com.example.spig_backend.model.Prijava;
import com.example.spig_backend.model.Utakmica;
import com.example.spig_backend.model.Pozicija;
import com.example.spig_backend.repository.PrijavaRepository;
import com.example.spig_backend.repository.UtakmicaRepository;
import com.example.spig_backend.repository.PozicijaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class PrijavaService {
    private final PrijavaRepository prijavaRepository;
    private final UtakmicaRepository utakmicaRepository;
    private final PozicijaRepository pozicijaRepository;

    public PrijavaService(PrijavaRepository prijavaRepository, UtakmicaRepository utakmicaRepository, PozicijaRepository pozicijaRepository) {
        this.prijavaRepository = prijavaRepository;
        this.utakmicaRepository = utakmicaRepository;
        this.pozicijaRepository = pozicijaRepository;
    }

    public List<Prijava> getPrijaveByUtakmica(Long utakmicaId) {
        return prijavaRepository.findByUtakmicaId(utakmicaId);
    }

    @Transactional
    public Prijava dodajPrijavuNaUtakmicu(Long utakmicaId, String imeIgraca, Long pozicijaId) {
        Utakmica utakmica = utakmicaRepository.findById(utakmicaId)
                .orElseThrow(() -> new RuntimeException("Utakmica nije pronađena"));

        Prijava prijava = new Prijava();
        prijava.setImeIgraca(imeIgraca);
        prijava.setUtakmica(utakmica);
        
        if (pozicijaId != null) {
            Pozicija pozicija = pozicijaRepository.findById(pozicijaId).orElse(null);
            prijava.setPozicija(pozicija);
        }

        if (utakmica.getTrenutnoPrijavljenih() >= utakmica.getKapacitet()) {
            prijava.setStatus("LISTA_CEKANJA");
        } else {
            prijava.setStatus("POTVRDENA");
            utakmica.setTrenutnoPrijavljenih(utakmica.getTrenutnoPrijavljenih() + 1);
            utakmicaRepository.save(utakmica);
        }

        return prijavaRepository.save(prijava);
    }

    @Transactional
    public void obrisiPrijavu(Long id) {
        Prijava prijava = prijavaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prijava nije pronađena"));
        
        Utakmica utakmica = prijava.getUtakmica();
        if ("POTVRDENA".equals(prijava.getStatus())) {
            utakmica.setTrenutnoPrijavljenih(utakmica.getTrenutnoPrijavljenih() - 1);
            utakmicaRepository.save(utakmica);
        }
        prijavaRepository.deleteById(id);
    }

    @Transactional
    public Prijava updatePrijava(Long id, String novoIme, Long pozicijaId) {
        Prijava prijava = prijavaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prijava nije pronađena"));
        prijava.setImeIgraca(novoIme);
        
        if (pozicijaId != null) {
            Pozicija pozicija = pozicijaRepository.findById(pozicijaId).orElse(null);
            prijava.setPozicija(pozicija);
        }
        
        return prijavaRepository.save(prijava);
    }
}
