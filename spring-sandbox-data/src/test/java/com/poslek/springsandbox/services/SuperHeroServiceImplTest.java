package com.poslek.springsandbox.services;

import com.poslek.springsandbox.model.SuperHero;
import com.poslek.springsandbox.repositories.SuperHeroRepository;
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
class SuperHeroServiceImplTest {

    public static final Long DEFAULT_ID = 1L;
    public static final String DEFAULT_FIRST_NAME = "foo";
    public static final String DEFAULT_LAST_NAME = "bar";
    public static final String DEFAULT_HERO_NAME = "baz";

    @InjectMocks
    SuperHeroServiceImpl testee;

    @Mock
    SuperHeroRepository superHeroRepository;

    SuperHero mockSuperHero;

    @BeforeEach
    void setUp() {
        this.mockSuperHero = SuperHero.builder()
                .id(DEFAULT_ID)
                .firstName(DEFAULT_FIRST_NAME)
                .lastName(DEFAULT_LAST_NAME)
                .heroName(DEFAULT_HERO_NAME)
                .build();
    }

    @Test
    void findAll() {
        Set<SuperHero> superHeroToFind = new HashSet<>();
        SuperHero secondSuperHero =
                SuperHero.builder().id(2L).firstName("foo2").lastName("bar2")
                        .heroName("baz2").build();
        superHeroToFind.add(mockSuperHero);
        superHeroToFind.add(secondSuperHero);
        when(superHeroRepository.findAll()).thenReturn(superHeroToFind);

        Set<SuperHero> foundSuperHeroes = testee.findAll();

        assertNotNull(foundSuperHeroes);
        assertEquals(2, foundSuperHeroes.size());
        verify(superHeroRepository).findAll();
    }

    @Test
    void findById() {
        when(superHeroRepository.findById(mockSuperHero.getId()))
                .thenReturn(java.util.Optional.ofNullable(mockSuperHero));

        SuperHero foundSuperHero = testee.findById(DEFAULT_ID);

        assertNotNull(foundSuperHero);
        assertEquals(foundSuperHero.getFirstName(), mockSuperHero.getFirstName());
        assertEquals(foundSuperHero.getLastName(), mockSuperHero.getLastName());
        assertEquals(foundSuperHero.getHeroName(), mockSuperHero.getHeroName());
        assertEquals(foundSuperHero.getId(), mockSuperHero.getId());
        verify(superHeroRepository).findById(any());
    }

    @Test
    void save() {
        SuperHero superHeroToSave = SuperHero.builder()
                .firstName(DEFAULT_FIRST_NAME)
                .lastName(DEFAULT_LAST_NAME)
                .heroName(DEFAULT_HERO_NAME)
                .build();
        when(superHeroRepository.save(superHeroToSave)).thenReturn(mockSuperHero);

        SuperHero savedSuperHero = testee.save(superHeroToSave);

        assertNotNull(savedSuperHero);
        assertEquals(savedSuperHero.getFirstName(), mockSuperHero.getFirstName());
        assertEquals(savedSuperHero.getLastName(), mockSuperHero.getLastName());
        assertEquals(savedSuperHero.getHeroName(), mockSuperHero.getHeroName());
        assertEquals(savedSuperHero.getId(), mockSuperHero.getId());
        verify(superHeroRepository).save(superHeroToSave);
    }

    @Test
    void delete() {
        testee.delete(mockSuperHero);

        verify(superHeroRepository).delete(mockSuperHero);
    }

    @Test
    void deleteById() {
        testee.deleteById(mockSuperHero.getId());

        verify(superHeroRepository).deleteById(mockSuperHero.getId());
    }
}