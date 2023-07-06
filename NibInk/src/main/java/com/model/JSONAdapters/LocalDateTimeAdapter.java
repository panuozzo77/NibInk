package com.model.JSONAdapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @Override
    public void write(JsonWriter out, LocalDateTime dateTime) throws IOException {
        out.value(dateTime.format(formatter));
    }

    @Override
    public LocalDateTime read(JsonReader in) throws IOException {
        String value = in.nextString();
        return LocalDateTime.parse(value, formatter);
    }
}