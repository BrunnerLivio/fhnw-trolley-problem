package ch.fhnw.webec.trolleyproblem.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

import ch.fhnw.webec.trolleyproblem.dtos.Position;

public class StringToEnumConverter implements Converter<String, Position> {
    @Override
    public Position convert(@NonNull String source) {
        try {
            return Position.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
