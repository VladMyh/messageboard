package com.messageboard.message.repository;

import com.messageboard.message.Message;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryMessageRepository implements MessageRepository {

    private static List<Message> messages = new ArrayList<>();

    @Override
    public List<Message> getMessages() {
        return messages;
    }

    @Override
    public void addMessage(Message msg) {
        messages.add(msg);
    }
}
