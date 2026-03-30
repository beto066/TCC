package br.unitins.model.converter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.OffsetDateTime;

import jakarta.ws.rs.ext.ParamConverter;
import jakarta.ws.rs.ext.ParamConverterProvider;
import jakarta.ws.rs.ext.Provider;

@Provider
public class OffsetDateTimeConverter implements ParamConverterProvider {
    @Override
    @SuppressWarnings("unchecked")
    public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
        if (rawType.equals(OffsetDateTime.class)) {
            return (ParamConverter<T>) new ParamConverter<OffsetDateTime>() {

                @Override
                public OffsetDateTime fromString(String value) {
                    if (value == null || value.isBlank()) return null;
                    return OffsetDateTime.parse(value);
                }

                @Override
                public String toString(OffsetDateTime value) {
                    return value.toString();
                }
            };
        }

        return null;
    }
}
