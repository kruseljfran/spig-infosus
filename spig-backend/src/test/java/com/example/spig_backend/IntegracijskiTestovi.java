package com.example.spig_backend;

import com.example.spig_backend.model.Sport;
import com.example.spig_backend.model.Utakmica;
import com.example.spig_backend.repository.SportRepository;
import com.example.spig_backend.repository.UtakmicaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegracijskiTestovi {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SportRepository sportRepository;

    @Autowired
    private com.example.spig_backend.repository.PrijavaRepository prijavaRepository;

    @Autowired
    private UtakmicaRepository utakmicaRepository;

    @Test
    public void testIntegracija_PristupUtakmicama() throws Exception {
        prijavaRepository.deleteAll();
        utakmicaRepository.deleteAll();
        sportRepository.deleteAll();

        Sport s = new Sport();
        s.setNaziv("Odbojka");
        s = sportRepository.save(s);

        Utakmica u = new Utakmica();
        u.setLokacija("Test lokacija");
        u.setDatumVrijeme(LocalDateTime.now().plusDays(1));
        u.setKapacitet(12);
        u.setTrenutnoPrijavljenih(0);
        u.setSport(s);
        utakmicaRepository.save(u);

        mockMvc.perform(get("/api/utakmice"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].lokacija").value("Test lokacija"));
    }
}
