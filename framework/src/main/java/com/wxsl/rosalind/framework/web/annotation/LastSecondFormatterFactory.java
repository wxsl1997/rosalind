package com.wxsl.rosalind.framework.web.annotation;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.wxsl.rosalind.framework.web.util.DateUtils;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


public class LastSecondFormatterFactory implements AnnotationFormatterFactory<LastSecond> {

    @Override
    public Set<Class<?>> getFieldTypes() {
        Set<Class<?>> fieldTypes = new HashSet<>();
        fieldTypes.add(LocalDateTime.class);
        return fieldTypes;
    }

    @Override
    public Printer<?> getPrinter(LastSecond annotation, Class<?> fieldType) {
        return (Printer<String>) (value, locale) -> value;
    }

    @Override
    public Parser<?> getParser(LastSecond annotation, Class<?> fieldType) {
        return (Parser<Object>) (text, locale) -> LocalDateTime.parse(text, DateUtils.DATE_TIME_FORMATTER).withSecond(59);
    }

    public static class LastSecondJsonDeserializer extends JsonDeserializer<LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return LocalDateTime.parse(jsonParser.getText(), DateUtils.DATE_TIME_FORMATTER).withSecond(59);
        }
    }

    public static class LastSecondJsonSerializer extends JsonSerializer<LocalDateTime> {
        @Override
        public void serialize(LocalDateTime dateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString(String.format("@LastSecond:%s", dateTime.format(DateUtils.DATE_TIME_FORMATTER)));
        }
    }
}
