package com.poslek.springsandbox.bootstrap;

import com.poslek.springsandbox.model.SuperHero;
import com.poslek.springsandbox.services.SuperHeroService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final SuperHeroService superheroService;

    public DataLoader(SuperHeroService superheroService) {
        this.superheroService = superheroService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Bootstrapping data");
        if (this.superheroService.findAll().size() == 0) {
            System.out.println("Adding superheroes");

            SuperHero batman =
                    SuperHero.builder()
                            .firstName("Bruce")
                            .lastName("Wayne")
                            .heroName("Batman")
                            .city("Gotham")
                            .build();

            this.superheroService.save(batman);

            SuperHero superman =
                    SuperHero.builder()
                            .firstName("Clark")
                            .lastName("Kent")
                            .heroName("Superman")
                            .city("Metropolis")
                            .build();

            this.superheroService.save(superman);
        }
    }

}
