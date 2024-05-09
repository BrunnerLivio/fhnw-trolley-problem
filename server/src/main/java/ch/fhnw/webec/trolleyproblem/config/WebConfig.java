package ch.fhnw.webec.trolleyproblem.config;

import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.format.FormatterRegistry;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ch.fhnw.webec.trolleyproblem.converters.StringToEnumConverter;

public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(@NonNull FormatterRegistry registry) {
        ApplicationConversionService.configure(registry);
        registry.addConverter(new StringToEnumConverter());
    }
}
