package com.poslek.springsandbox.services;

import com.poslek.springsandbox.model.City;
import com.poslek.springsandbox.repositories.CityRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public Set<City> findAll() {
        Set<City> cities = new HashSet<>();
        this.cityRepository.findAll().forEach(cities::add);

        return cities;
    }

    @Override
    public City findById(Long id) {
        return this.cityRepository.findById(id).orElse(null);
    }

    @Override
    public City save(City city) {
        return this.cityRepository.save(city);
    }

    @Override
    public void delete(City city) {
        this.cityRepository.delete(city);
    }

    @Override
    public void deleteById(Long id) {
        this.cityRepository.deleteById(id);
    }

}
