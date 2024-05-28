package ch.fhnw.webec.trolleyproblem.controllers;

import ch.fhnw.webec.trolleyproblem.Application;
import ch.fhnw.webec.trolleyproblem.config.SecurityConfiguration;
import ch.fhnw.webec.trolleyproblem.config.WebConfig;
import ch.fhnw.webec.trolleyproblem.dtos.VictimDto;
import ch.fhnw.webec.trolleyproblem.entities.VictimEntity;
import ch.fhnw.webec.trolleyproblem.repositories.VictimRepository;
import ch.fhnw.webec.trolleyproblem.services.VictimService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(VictimController.class)
@ContextConfiguration(classes={Application.class, WebConfig.class, SecurityConfiguration.class})
public class VictimControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VictimService victimService;

    @MockBean
    private VictimRepository victimRepository;

    @Test
    public void testList() throws Exception {
        when(victimService.findAll()).thenReturn(List.of(
            new VictimDto(1L, "Person", "https://s3.com/person.jpg"),
            new VictimDto(2L, "Animal", "https://s3.com/animal.jpg")
        ));

        this.mockMvc.perform(get("/api/victims/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Person"))
                .andExpect(jsonPath("$[0].imageUrl").value("https://s3.com/person.jpg"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Animal"))
                .andExpect(jsonPath("$[1].imageUrl").value("https://s3.com/animal.jpg"));
    }
}
