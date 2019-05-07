package com.poslek.springsandbox.serialiazers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.poslek.springsandbox.model.SuperHero;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CustomSuperHeroSerializer extends StdSerializer<Set<SuperHero>> {

    public CustomSuperHeroSerializer() {
        this(null);
    }

    public CustomSuperHeroSerializer(Class<Set<SuperHero>> t) {
        super(t);
    }

    @Override
    public void serialize(
            Set<SuperHero> superHeroes,
            JsonGenerator jsonGenerator,
            SerializerProvider serializerProvider)
            throws IOException {

        Set<SuperHero> heroes = new HashSet<>();
        for (SuperHero h : superHeroes) {
            h.setPowers(null);
            h.setCity(null);
            heroes.add(h);
        }
        jsonGenerator.writeObject(heroes);

    }

}
