package com.app.chat.service.impl;

import com.app.chat.entity.ChatMessage;
import com.app.chat.repository.ChatMessageRepo;
import com.app.chat.service.ChatMessageService;
import com.app.chat.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {

    private final ChatMessageRepo chatMessageRepo;
    private final ChatRoomService chatRoomService;


    @Override
    public ChatMessage save(ChatMessage chatMessage) {
        String chatId = chatRoomService.getChatRoomId(chatMessage.getSenderId(),
                        chatMessage.getRecipientId(), true)
                .orElseThrow(() -> new RuntimeException("No chat room found"));
        chatMessage.setChatId(chatId);
        return chatMessageRepo.save(chatMessage);
    }

    @Override
    public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
        Optional<String> chatId = chatRoomService.getChatRoomId(senderId,
                recipientId, false);
        return chatId.map(chatMessageRepo::findByChatId)
                .orElse(new ArrayList<>());
    }

}
