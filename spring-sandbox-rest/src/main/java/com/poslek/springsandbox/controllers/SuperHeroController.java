package com.poslek.springsandbox.controllers;

import com.poslek.springsandbox.model.SuperHero;
import com.poslek.springsandbox.services.SuperHeroService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/superheroes")
public class SuperHeroController {

    private final SuperHeroService superheroService;

    public SuperHeroController(SuperHeroService superheroService) {
        this.superheroService = superheroService;
    }

    @GetMapping("")
    public Set<SuperHero> superHeroes() {
        return this.superheroService.findAll();
    }

    @GetMapping("/{id}")
    public SuperHero superHero(@PathVariable Long id) {
        SuperHero superHero = this.superheroService.findById(id);

        if (superHero == null) {
            throw new RuntimeException("Could not find superhero with ID " + id);
        }

        return superHero;
    }

    @PostMapping("")
    public SuperHero addSuperHero(@RequestBody SuperHero superHero) {
        return this.superheroService.save(superHero);
    }

    @PutMapping("/{id}")
    public SuperHero updateSuperHero(@RequestBody SuperHero updateSuperHero, @PathVariable Long id) {
        SuperHero superHero = this.superheroService.findById(id);

        if (superHero == null) {
            throw new RuntimeException("Could not find superhero with ID " + id);
        }

        superHero.setFirstName(updateSuperHero.getFirstName());
        superHero.setLastName(updateSuperHero.getLastName());
        superHero.setHeroName(updateSuperHero.getHeroName());
        superHero.setCity(updateSuperHero.getCity());
        superHero.setPowers(updateSuperHero.getPowers());

        return this.superheroService.save(superHero);
    }

    @DeleteMapping("/{id}")
    public void deleteSuperHero(@PathVariable Long id) {
        this.superheroService.deleteById(id);
    }

}
