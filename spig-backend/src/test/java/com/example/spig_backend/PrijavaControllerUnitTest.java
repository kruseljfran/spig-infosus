package com.example.spig_backend;

import com.example.spig_backend.controller.PrijavaController;
import com.example.spig_backend.model.Prijava;
import com.example.spig_backend.service.PrijavaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class PrijavaControllerUnitTest {

    @Test
    public void testDodajPrijavu() throws Exception {
        PrijavaService mockService = Mockito.mock(PrijavaService.class);
        PrijavaController controller = new PrijavaController(mockService);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        Prijava mockOdgovor = new Prijava();
        mockOdgovor.setId(1L);
        mockOdgovor.setImeIgraca("Igrač Prezentacija");
        mockOdgovor.setStatus("POTVRDENA");

        Mockito.when(mockService.dodajPrijavuNaUtakmicu(Mockito.eq(1L), Mockito.eq("Igrač Prezentacija"), Mockito.isNull()))
               .thenReturn(mockOdgovor);

        mockMvc.perform(post("/api/prijave/utakmica/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"imeIgraca\": \"Igrač Prezentacija\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.imeIgraca").value("Igrač Prezentacija"))
                .andExpect(jsonPath("$.status").value("POTVRDENA"));
    }
}
