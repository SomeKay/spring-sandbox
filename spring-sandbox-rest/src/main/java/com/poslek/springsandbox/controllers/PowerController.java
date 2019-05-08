package com.poslek.springsandbox.controllers;

import com.poslek.springsandbox.model.Power;
import com.poslek.springsandbox.services.PowerService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/powers")
public class PowerController {

    private final PowerService powerService;

    public PowerController(PowerService powerService) {
        this.powerService = powerService;
    }

    @GetMapping("")
    public Set<Power> powers() {
        return this.powerService.findAll();
    }

    @GetMapping("/{id}")
    public Power power(@PathVariable Long id) {
        Power power = this.powerService.findById(id);

        if (power == null) {
            throw new RuntimeException("Could not find power with ID " + id);
        }

        return power;
    }

    @PostMapping("")
    public Power addPower(@RequestBody Power power) {
        return this.powerService.save(power);
    }

    @PutMapping("/{id}")
    public Power updatePower(@RequestBody Power updatePower, @PathVariable Long id) {
        Power power = this.powerService.findById(id);

        if (power == null) {
            throw new RuntimeException("Could not find power with ID " + id);
        }

        power.setName(updatePower.getName());
        power.setStrength(updatePower.getStrength());
        power.setSuperHeroes(updatePower.getSuperHeroes());

        return this.powerService.save(power);
    }

    @DeleteMapping("/{id}")
    public void deletePower(@PathVariable Long id) {
        this.powerService.deleteById(id);
    }

}
