package com.poslek.springsandbox.serialiazers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.poslek.springsandbox.model.City;

import java.io.IOException;

public class CustomCitySerializer extends StdSerializer<City> {

    public CustomCitySerializer() {
        this(null);
    }

    public CustomCitySerializer(Class<City> t) {
        super(t);
    }

    @Override
    public void serialize(
            City city,
            JsonGenerator jsonGenerator,
            SerializerProvider serializerProvider)
            throws IOException {

        city.setSuperHeroes(null);
        jsonGenerator.writeObject(city);
    }

}
