package com.messageboard.message.repository;

import com.messageboard.message.Message;

import java.util.List;

public interface MessageRepository {
    List<Message> getMessages();
    void addMessage(Message msg);
}
