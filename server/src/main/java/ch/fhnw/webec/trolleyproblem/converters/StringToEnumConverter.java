package ch.fhnw.webec.trolleyproblem.converters;

import ch.fhnw.webec.trolleyproblem.entities.Directional;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

public class StringToEnumConverter implements Converter<String, Directional> {
    @Override
    public Directional convert(@NonNull String source) {
        try {
            return Directional.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
