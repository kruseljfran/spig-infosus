package com.example.spig_backend;

import com.example.spig_backend.model.Prijava;
import com.example.spig_backend.model.Utakmica;
import com.example.spig_backend.repository.PozicijaRepository;
import com.example.spig_backend.repository.PrijavaRepository;
import com.example.spig_backend.repository.UtakmicaRepository;
import com.example.spig_backend.service.PrijavaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PrijavaServiceUnitTest {

    @Mock
    private PrijavaRepository prijavaRepository;

    @Mock
    private UtakmicaRepository utakmicaRepository;

    @Mock
    private PozicijaRepository pozicijaRepository;

    @InjectMocks
    private PrijavaService prijavaService;

    @Test
    public void testDodajPrijavu_KadImaMjesta_StatusPotvrdena() {
        Utakmica u = new Utakmica();
        u.setId(1L);
        u.setKapacitet(10);
        u.setTrenutnoPrijavljenih(5);

        when(utakmicaRepository.findById(1L)).thenReturn(Optional.of(u));
        when(prijavaRepository.save(any(Prijava.class))).thenAnswer(i -> i.getArguments()[0]);

        Prijava rezultat = prijavaService.dodajPrijavuNaUtakmicu(1L, "Ivan Ivic", null);

        assertEquals("POTVRDENA", rezultat.getStatus());
        assertEquals(6, u.getTrenutnoPrijavljenih());
        verify(utakmicaRepository, times(1)).save(u);
    }

    @Test
    public void testDodajPrijavu_KadJePuno_StatusListaCekanja() {
        Utakmica u = new Utakmica();
        u.setId(1L);
        u.setKapacitet(10);
        u.setTrenutnoPrijavljenih(10);

        when(utakmicaRepository.findById(1L)).thenReturn(Optional.of(u));
        when(prijavaRepository.save(any(Prijava.class))).thenAnswer(i -> i.getArguments()[0]);

        Prijava rezultat = prijavaService.dodajPrijavuNaUtakmicu(1L, "Marko Maric", null);

        assertEquals("LISTA_CEKANJA", rezultat.getStatus());
        assertEquals(10, u.getTrenutnoPrijavljenih());
        verify(utakmicaRepository, never()).save(u);
    }
}
