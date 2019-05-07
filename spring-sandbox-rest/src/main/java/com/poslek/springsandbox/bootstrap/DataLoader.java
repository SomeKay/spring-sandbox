package com.poslek.springsandbox.bootstrap;

import com.poslek.springsandbox.model.City;
import com.poslek.springsandbox.model.SuperHero;
import com.poslek.springsandbox.services.CityService;
import com.poslek.springsandbox.services.SuperHeroService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final SuperHeroService superheroService;
    private final CityService cityService;

    public DataLoader(SuperHeroService superheroService, CityService cityService) {
        this.superheroService = superheroService;
        this.cityService = cityService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Bootstrapping data");
        if (this.superheroService.findAll().size() == 0) {
            System.out.println("Adding cities");

            City gotham = City.builder().name("Gotham").build();
            this.cityService.save(gotham);

            City metropolis = City.builder().name("Metropolis").build();
            this.cityService.save(metropolis);

            System.out.println("Adding superheroes");

            SuperHero batman =
                    SuperHero.builder()
                            .firstName("Bruce")
                            .lastName("Wayne")
                            .heroName("Batman")
                            .city(gotham)
                            .build();
            this.superheroService.save(batman);

            SuperHero robin =
                    SuperHero.builder()
                            .firstName("Dick")
                            .lastName("Grayson")
                            .heroName("Batman")
                            .city(gotham)
                            .build();
            this.superheroService.save(robin);

            SuperHero superman =
                    SuperHero.builder()
                            .firstName("Clark")
                            .lastName("Kent")
                            .heroName("Superman")
                            .city(metropolis)
                            .build();
            this.superheroService.save(superman);
        }
    }

}
