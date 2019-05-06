package com.poslek.springsandbox.services;

import com.poslek.springsandbox.model.SuperHero;
import com.poslek.springsandbox.repositories.SuperHeroRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class SuperHeroServiceImpl implements SuperheroService {

    private final SuperHeroRepository superHeroRepository;

    public SuperHeroServiceImpl(SuperHeroRepository superHeroRepository) {
        this.superHeroRepository = superHeroRepository;
    }

    @Override
    public Set<SuperHero> findAll() {
        Set<SuperHero> superheroes = new HashSet<>();
        this.superHeroRepository.findAll().forEach(superheroes::add);

        return superheroes;
    }

    @Override
    public SuperHero findById(Long id) {
        return this.superHeroRepository.findById(id).orElse(null);
    }

    @Override
    public SuperHero save(SuperHero superHero) {
        return this.superHeroRepository.save(superHero);
    }

    @Override
    public void delete(SuperHero superHero) {
        this.superHeroRepository.delete(superHero);
    }

    @Override
    public void deleteById(Long id) {
        this.superHeroRepository.deleteById(id);
    }

}
