package com.poslek.springsandbox.serialiazers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.poslek.springsandbox.model.Power;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CustomPowerSerializer extends StdSerializer<Set<Power>> {

    public CustomPowerSerializer() {
        this(null);
    }

    public CustomPowerSerializer(Class<Set<Power>> t) {
        super(t);
    }

    @Override
    public void serialize(
            Set<Power> powers,
            JsonGenerator jsonGenerator,
            SerializerProvider serializerProvider)
            throws IOException {

        Set<Power> pows = new HashSet<>();
        for (Power p : powers) {
            p.setSuperheroes(null);
            pows.add(p);
        }
        jsonGenerator.writeObject(pows);

    }


}
