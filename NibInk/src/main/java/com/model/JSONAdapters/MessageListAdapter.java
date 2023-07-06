package com.model.JSONAdapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.model.Message;

import java.io.IOException;
import java.util.List;

public class MessageListAdapter extends TypeAdapter<List<Message>> {

    private final TypeAdapter<Message> messageAdapter;

    public MessageListAdapter(TypeAdapter<Message> messageAdapter) {
        this.messageAdapter = messageAdapter;
    }

    @Override
    public void write(JsonWriter out, List<Message> messages) throws IOException {
        out.beginArray();
        for (Message message : messages) {
            messageAdapter.write(out, message);
        }
        out.endArray();
    }

    @Override
    public List<Message> read(JsonReader in) throws IOException {
        // You can implement this method if needed for deserialization
        throw new UnsupportedOperationException("Deserialization is not supported");
    }
}
