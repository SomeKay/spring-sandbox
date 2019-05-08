package com.poslek.springsandbox.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poslek.springsandbox.model.City;
import com.poslek.springsandbox.services.CityService;
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
public class CityControllerTest {

    @Mock
    CityService cityService;

    @InjectMocks
    CityController testee;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(testee).build();
    }

    @Test
    public void cities() throws Exception {
        Set<City> mockedCities = new HashSet<>();
        mockedCities
                .add(City.builder().id(1L).name("foo").build());
        mockedCities
                .add(City.builder().id(2L).name("bar").build());

        when(cityService.findAll()).thenReturn(mockedCities);

        mockMvc.perform(get("/cities"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json("[{'id':1,'name':'foo'}," +
                                "{'id':2,'name':'bar'}]"));
    }

    @Test
    public void city() throws Exception {
        City mockedCity = City.builder().id(1L).name("foo")
                .build();
        when(cityService.findById(1L)).thenReturn(mockedCity);

        mockMvc.perform(get("/cities/1"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json("{'id':1,'name':'foo'}"));
    }

    @Test
    public void addCity() throws Exception {
        City mockedCity = City.builder().id(1L).name("foo")
                .build();
        String content = new ObjectMapper().writeValueAsString(mockedCity);

        mockMvc.perform(
                post("/cities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk());
        verify(cityService).save(mockedCity);
    }

    @Test
    public void updateCity() throws Exception {
        City mockedCity = City.builder().id(1L).name("foo")
                .build();
        String content = new ObjectMapper().writeValueAsString(mockedCity);
        when(cityService.findById(1L)).thenReturn(mockedCity);

        mockMvc.perform(
                put("/cities/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk());
        verify(cityService).save(mockedCity);
    }

    @Test
    public void deleteCity() throws Exception {
        mockMvc.perform(
                delete("/cities/1"))
                .andExpect(status().isOk());
        verify(cityService).deleteById(1L);
    }
}
