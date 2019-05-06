package com.poslek.springsandbox.repositories;

import com.poslek.springsandbox.model.SuperHero;
import org.springframework.data.repository.CrudRepository;

public interface SuperHeroRepository extends CrudRepository<SuperHero, Long> {
}
