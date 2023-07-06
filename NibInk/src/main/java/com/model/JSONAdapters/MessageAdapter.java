package com.model.JSONAdapters;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.model.Message;

public class MessageAdapter extends TypeAdapter<Message> {
    
	private final LocalDateTimeAdapter localDateTimeAdapter = new LocalDateTimeAdapter();
    // Implement the write() method for serialization
    @Override
    public void write(JsonWriter out, Message message) throws IOException {
        out.beginObject();
        out.name("id").value(message.getId());
        out.name("userId").value(message.getUserId());
        out.name("userEmail").value(message.getUserEmail());
        out.name("sent");
        localDateTimeAdapter.write(out, message.getSent());
        out.name("text").value(message.getText());
        out.name("status").value(message.getStatus());
        out.name("messageNumber").value(message.getMessageNumber());
        out.name("hasAdminReadIt").value(message.getAdminRead());
        out.name("hasUserReadIt").value(message.getUserRead());
        out.endObject();
    }

    // Implement the read() method for deserialization
    @Override
    public Message read(JsonReader in) throws IOException {
        // You can implement this method if needed for deserialization
        throw new UnsupportedOperationException("Deserialization is not supported");
    }
}
