package com.poslek.springsandbox.repositories;

import com.poslek.springsandbox.model.City;
import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository<City, Long> {
}
