package com.poslek.springsandbox.bootstrap;

import com.poslek.springsandbox.model.City;
import com.poslek.springsandbox.model.Power;
import com.poslek.springsandbox.model.SuperHero;
import com.poslek.springsandbox.services.CityService;
import com.poslek.springsandbox.services.PowerService;
import com.poslek.springsandbox.services.SuperHeroService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final SuperHeroService superheroService;
    private final CityService cityService;
    private final PowerService powerService;

    public DataLoader(
            SuperHeroService superheroService,
            CityService cityService,
            PowerService powerService) {
        this.superheroService = superheroService;
        this.cityService = cityService;
        this.powerService = powerService;
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


            System.out.println("Adding powers");

            Power karate = Power.builder()
                    .name("Karate")
                    .strength(10.0f)
                    .build();
            this.powerService.save(karate);

            Power highIntelligence = Power.builder()
                    .name("High intelligence")
                    .strength(50.0f)
                    .build();
            this.powerService.save(highIntelligence);

            Power agility = Power.builder()
                    .name("Agility")
                    .strength(10.0f)
                    .build();
            this.powerService.save(agility);

            Power flying = Power.builder()
                    .name("Flying")
                    .strength(1000.0f)
                    .build();
            this.powerService.save(flying);

            Power superHumanStrength = Power.builder()
                    .name("Super-human strength")
                    .strength(2000.0f)
                    .build();
            this.powerService.save(superHumanStrength);


            System.out.println("Adding superheroes");

            SuperHero batman =
                    SuperHero.builder()
                            .firstName("Bruce")
                            .lastName("Wayne")
                            .heroName("Batman")
                            .city(gotham)
                            .build();
            batman.getPowers().add(karate);
            batman.getPowers().add(highIntelligence);
            this.superheroService.save(batman);

            SuperHero robin =
                    SuperHero.builder()
                            .firstName("Dick")
                            .lastName("Grayson")
                            .heroName("Robin")
                            .city(gotham)
                            .build();
            robin.getPowers().add(karate);
            robin.getPowers().add(agility);
            this.superheroService.save(robin);

            SuperHero superman =
                    SuperHero.builder()
                            .firstName("Clark")
                            .lastName("Kent")
                            .heroName("Superman")
                            .city(metropolis)
                            .build();
            superman.getPowers().add(flying);
            superman.getPowers().add(agility);
            superman.getPowers().add(superHumanStrength);
            this.superheroService.save(superman);

        }
    }

}
