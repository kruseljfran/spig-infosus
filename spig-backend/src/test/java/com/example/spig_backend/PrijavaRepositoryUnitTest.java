package com.example.spig_backend;

import com.example.spig_backend.model.Prijava;
import com.example.spig_backend.model.Utakmica;
import com.example.spig_backend.repository.PrijavaRepository;
import com.example.spig_backend.repository.UtakmicaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class PrijavaRepositoryUnitTest {

    @Autowired
    private PrijavaRepository prijavaRepository;

    @Autowired
    private UtakmicaRepository utakmicaRepository;

    @Autowired
    private com.example.spig_backend.repository.SportRepository sportRepository;

    @Test
    public void testSpremanjeIDohvatPrijave() {
        com.example.spig_backend.model.Sport sport = new com.example.spig_backend.model.Sport();
        sport.setNaziv("Testni Sport");
        sport = sportRepository.save(sport);

        Utakmica utakmica = new Utakmica();
        utakmica.setLokacija("Zadar");
        utakmica.setDatumVrijeme(java.time.LocalDateTime.parse("2026-06-01T20:00"));
        utakmica.setKapacitet(10);
        utakmica.setTrenutnoPrijavljenih(0);
        utakmica.setSport(sport);
        utakmica = utakmicaRepository.save(utakmica);

        Prijava prijava = new Prijava();
        prijava.setImeIgraca("Podatkovni Sloj Test");
        prijava.setStatus("POTVRDENA");
        prijava.setUtakmica(utakmica);
        
        Prijava spremljenaPrijava = prijavaRepository.save(prijava);

        assertNotNull(spremljenaPrijava.getId());
        
        Prijava izBaze = prijavaRepository.findById(spremljenaPrijava.getId()).get();
        assertEquals("Podatkovni Sloj Test", izBaze.getImeIgraca());
    }
}
