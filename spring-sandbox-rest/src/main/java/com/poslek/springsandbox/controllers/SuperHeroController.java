package com.poslek.springsandbox.controllers;

import com.poslek.springsandbox.model.SuperHero;
import com.poslek.springsandbox.services.SuperheroService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class SuperHeroController {

    private final SuperheroService superheroService;

    public SuperHeroController(SuperheroService superheroService) {
        this.superheroService = superheroService;
    }

    @GetMapping("/superheroes")
    public Set<SuperHero> superHeroes() {
        return this.superheroService.findAll();
    }

    @GetMapping("/superheroes/{id}")
    public SuperHero superHero(@PathVariable Long id) {
        SuperHero superHero = this.superheroService.findById(id);

        if (superHero == null) {
            throw new RuntimeException("Could not find superhero with ID " + id);
        }

        return superHero;
    }

    @PostMapping("/superheroes")
    public SuperHero addSuperHero(@RequestBody SuperHero superHero) {
        return this.superheroService.save(superHero);
    }

    @PutMapping("/superheroes/{id}")
    public SuperHero updateSuperHero(@RequestBody SuperHero updateSuperHero, @PathVariable Long id) {
        SuperHero superHero = this.superheroService.findById(id);

        if (superHero == null) {
            throw new RuntimeException("Could not find superhero with ID " + id);
        }

        superHero.setFirstName(updateSuperHero.getFirstName());
        superHero.setLastName(updateSuperHero.getLastName());
        superHero.setHeroName(updateSuperHero.getHeroName());
        superHero.setCity(updateSuperHero.getCity());

        return this.superheroService.save(superHero);
    }

    @DeleteMapping("/superheroes/{id}")
    public void deleteSuperHero(@PathVariable Long id) {
        this.superheroService.deleteById(id);
    }

}
