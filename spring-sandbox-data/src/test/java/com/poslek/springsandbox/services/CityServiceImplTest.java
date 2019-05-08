package com.poslek.springsandbox.services;

import com.poslek.springsandbox.model.City;
import com.poslek.springsandbox.repositories.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CityServiceImplTest {

    public static final Long DEFAULT_ID = 1L;
    public static final String DEFAULT_NAME = "foo";

    @InjectMocks
    CityServiceImpl testee;

    @Mock
    CityRepository cityRepository;

    City mockCity;

    @BeforeEach
    void setUp() {
        this.mockCity = City.builder()
                .name(DEFAULT_NAME)
                .id(DEFAULT_ID)
                .build();
    }

    @Test
    void findAll() {
        Set<City> citiesToFind = new HashSet<>();
        City secondCity = City.builder().id(2L).name("bar").build();
        citiesToFind.add(mockCity);
        citiesToFind.add(secondCity);
        when(cityRepository.findAll()).thenReturn(citiesToFind);

        Set<City> foundCities = testee.findAll();

        assertNotNull(foundCities);
        assertEquals(2, foundCities.size());
        verify(cityRepository).findAll();
    }

    @Test
    void findById() {
        when(cityRepository.findById(mockCity.getId()))
                .thenReturn(java.util.Optional.ofNullable(mockCity));

        City foundCity = testee.findById(DEFAULT_ID);

        assertNotNull(foundCity);
        assertEquals(foundCity.getName(), mockCity.getName());
        assertEquals(foundCity.getId(), mockCity.getId());
        verify(cityRepository).findById(any());
    }

    @Test
    void save() {
        City cityToSave = City.builder().name(DEFAULT_NAME).id(DEFAULT_ID)
                .build();
        when(cityRepository.save(cityToSave)).thenReturn(mockCity);

        City savedCity = testee.save(cityToSave);

        assertNotNull(savedCity);
        assertEquals(savedCity.getId(), mockCity.getId());
        assertEquals(savedCity.getName(), mockCity.getName());
        verify(cityRepository).save(cityToSave);
    }

    @Test
    void delete() {
        testee.delete(mockCity);

        verify(cityRepository).delete(mockCity);
    }

    @Test
    void deleteById() {
        testee.deleteById(mockCity.getId());

        verify(cityRepository).deleteById(mockCity.getId());
    }
}