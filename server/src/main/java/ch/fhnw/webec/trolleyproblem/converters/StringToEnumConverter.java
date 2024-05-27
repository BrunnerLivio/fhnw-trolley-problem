package ch.fhnw.webec.trolleyproblem.converters;

import ch.fhnw.webec.trolleyproblem.dtos.Position;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

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
