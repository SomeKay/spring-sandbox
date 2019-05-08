package com.poslek.springsandbox.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poslek.springsandbox.model.SuperHero;
import com.poslek.springsandbox.services.SuperHeroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class SuperHeroControllerTest {

    @Mock
    SuperHeroService superHeroService;

    @InjectMocks
    SuperHeroController testee;

    Set<SuperHero> mockedSuperheroes;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockedSuperheroes = new HashSet<>();
        mockedSuperheroes
                .add(SuperHero.builder().id(1L).firstName("foo").build());
        mockedSuperheroes
                .add(SuperHero.builder().id(2L).firstName("bar").build());

        mockMvc = MockMvcBuilders.standaloneSetup(testee).build();
    }

    @Test
    public void superHeroes() throws Exception {
        when(superHeroService.findAll()).thenReturn(mockedSuperheroes);

        mockMvc.perform(get("/superheroes"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json("[{'id':1,'firstName':'foo'}," +
                                "{'id':2,'firstName':'bar'}]"));
    }

    @Test
    public void superHero() throws Exception {
        SuperHero mockedSuperHero = SuperHero.builder().id(1L).firstName("foo")
                .build();
        when(superHeroService.findById(1L)).thenReturn(mockedSuperHero);

        mockMvc.perform(get("/superheroes/1"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json("{'id':1,'firstName':'foo'}"));
    }

    @Test
    public void addSuperHero() throws Exception {
        SuperHero mockedSuperHero = SuperHero.builder().id(1L).firstName("foo")
                .build();
        String content = new ObjectMapper().writeValueAsString(mockedSuperHero);

        mockMvc.perform(
                post("/superheroes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk());
        verify(superHeroService).save(mockedSuperHero);
    }

    @Test
    public void updateSuperHero() throws Exception {
        SuperHero mockedSuperHero = SuperHero.builder().id(1L).firstName("foo")
                .build();
        String content = new ObjectMapper().writeValueAsString(mockedSuperHero);
        when(superHeroService.findById(1L)).thenReturn(mockedSuperHero);

        mockMvc.perform(
                put("/superheroes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk());
        verify(superHeroService).save(mockedSuperHero);
    }

    @Test
    public void deleteSuperHero() throws Exception {
        mockMvc.perform(
                delete("/superheroes/1"))
                .andExpect(status().isOk());
        verify(superHeroService).deleteById(1L);
    }
}
