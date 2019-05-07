package com.poslek.springsandbox.controllers;

import com.poslek.springsandbox.model.City;
import com.poslek.springsandbox.services.CityService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("")
    public Set<City> cities() {
        return this.cityService.findAll();
    }

    @GetMapping("/{id}")
    public City city(@PathVariable Long id) {
        City city = this.cityService.findById(id);

        if (city == null) {
            throw new RuntimeException("Could not find city with ID " + id);
        }

        return city;
    }

    @PostMapping("")
    public City addCity(@RequestBody City city) {
        return this.cityService.save(city);
    }

    @PutMapping("/{id}")
    public City updateCity(@RequestBody City updateCity, @PathVariable Long id) {
        City city = this.cityService.findById(id);

        if (city == null) {
            throw new RuntimeException("Could not find city with ID " + id);
        }

        city.setName(updateCity.getName());

        return this.cityService.save(city);
    }

    @DeleteMapping("/{id}")
    public void deleteCity(@PathVariable Long id) {
        this.cityService.deleteById(id);
    }
}
