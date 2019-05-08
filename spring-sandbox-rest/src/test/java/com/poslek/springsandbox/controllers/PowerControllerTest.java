package com.poslek.springsandbox.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poslek.springsandbox.model.Power;
import com.poslek.springsandbox.services.PowerService;
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
public class PowerControllerTest {

    @Mock
    PowerService powerService;

    @InjectMocks
    PowerController testee;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(testee).build();
    }

    @Test
    public void powers() throws Exception {
        Set<Power> mockedPowers = new HashSet<>();
        mockedPowers
                .add(Power.builder().id(1L).name("foo").build());
        mockedPowers
                .add(Power.builder().id(2L).name("bar").build());

        when(powerService.findAll()).thenReturn(mockedPowers);

        mockMvc.perform(get("/powers"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json("[{'id':1,'name':'foo'}," +
                                "{'id':2,'name':'bar'}]"));
    }

    @Test
    public void power() throws Exception {
        Power mockedPower = Power.builder().id(1L).name("foo")
                .build();
        when(powerService.findById(1L)).thenReturn(mockedPower);

        mockMvc.perform(get("/powers/1"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json("{'id':1,'name':'foo'}"));
    }

    @Test
    public void addPower() throws Exception {
        Power mockedPower = Power.builder().id(1L).name("foo")
                .build();
        String content = new ObjectMapper().writeValueAsString(mockedPower);

        mockMvc.perform(
                post("/powers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk());
        verify(powerService).save(mockedPower);
    }

    @Test
    public void updatePower() throws Exception {
        Power mockedPower = Power.builder().id(1L).name("foo")
                .build();
        String content = new ObjectMapper().writeValueAsString(mockedPower);
        when(powerService.findById(1L)).thenReturn(mockedPower);

        mockMvc.perform(
                put("/powers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk());
        verify(powerService).save(mockedPower);
    }

    @Test
    public void deletePower() throws Exception {
        mockMvc.perform(
                delete("/powers/1"))
                .andExpect(status().isOk());
        verify(powerService).deleteById(1L);
    }
}
