package com.tskifyX.TaskifyX.service;

import com.tskifyX.TaskifyX.model.Chat;
import com.tskifyX.TaskifyX.repository.ChatRepository;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {
    private ChatRepository chatRepository;
    @Override
    public Chat createChat(Chat chat) {
        return chatRepository.save(chat);
    }
}
