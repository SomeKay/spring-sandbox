package com.poslek.springsandbox.services;

import com.poslek.springsandbox.model.Power;
import com.poslek.springsandbox.repositories.PowerRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class PowerServiceImpl implements PowerService {

    private final PowerRepository powerRepository;

    public PowerServiceImpl(PowerRepository powerRepository) {
        this.powerRepository = powerRepository;
    }

    @Override
    public Set<Power> findAll() {
        Set<Power> powers = new HashSet<>();
        this.powerRepository.findAll().forEach(powers::add);

        return powers;
    }

    @Override
    public Power findById(Long id) {
        return this.powerRepository.findById(id).orElse(null);
    }

    @Override
    public Power save(Power power) {
        return this.powerRepository.save(power);
    }

    @Override
    public void delete(Power power) {
        this.powerRepository.delete(power);
    }

    @Override
    public void deleteById(Long id) {
        this.powerRepository.deleteById(id);
    }

}
