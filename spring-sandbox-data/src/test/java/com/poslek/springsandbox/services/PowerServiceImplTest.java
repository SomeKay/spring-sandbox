package com.poslek.springsandbox.services;

import com.poslek.springsandbox.model.Power;
import com.poslek.springsandbox.repositories.PowerRepository;
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
class PowerServiceImplTest {

    public static final Long DEFAULT_ID = 1L;
    public static final String DEFAULT_NAME = "foo";
    public static final Float DEFAULT_STRENGTH = 1f;

    @InjectMocks
    PowerServiceImpl testee;

    @Mock
    PowerRepository powerRepository;

    Power mockPower;

    @BeforeEach
    void setUp() {
        this.mockPower = Power.builder()
                .name(DEFAULT_NAME)
                .strength(DEFAULT_STRENGTH)
                .id(DEFAULT_ID)
                .build();
    }

    @Test
    void findAll() {
        Set<Power> powersToFind = new HashSet<>();
        Power secondPower = Power.builder().id(2L).name("bar").strength(2F).build();
        powersToFind.add(mockPower);
        powersToFind.add(secondPower);
        when(powerRepository.findAll()).thenReturn(powersToFind);

        Set<Power> foundPowers = testee.findAll();

        assertNotNull(foundPowers);
        assertEquals(2, foundPowers.size());
        verify(powerRepository).findAll();
    }

    @Test
    void findById() {
        when(powerRepository.findById(mockPower.getId()))
                .thenReturn(java.util.Optional.ofNullable(mockPower));

        Power foundPower = testee.findById(DEFAULT_ID);

        assertNotNull(foundPower);
        assertEquals(foundPower.getName(), mockPower.getName());
        assertEquals(foundPower.getStrength(), mockPower.getStrength());
        assertEquals(foundPower.getId(), mockPower.getId());
        verify(powerRepository).findById(any());
    }

    @Test
    void save() {
        Power powerToSave = Power.builder()
                .name(DEFAULT_NAME)
                .id(DEFAULT_ID)
                .strength(DEFAULT_STRENGTH)
                .build();
        when(powerRepository.save(powerToSave)).thenReturn(mockPower);

        Power savedPower = testee.save(powerToSave);

        assertNotNull(savedPower);
        assertEquals(savedPower.getId(), mockPower.getId());
        assertEquals(savedPower.getName(), mockPower.getName());
        assertEquals(savedPower.getStrength(), mockPower.getStrength());
        verify(powerRepository).save(powerToSave);
    }

    @Test
    void delete() {
        testee.delete(mockPower);

        verify(powerRepository).delete(mockPower);
    }

    @Test
    void deleteById() {
        testee.deleteById(mockPower.getId());

        verify(powerRepository).deleteById(mockPower.getId());
    }
}