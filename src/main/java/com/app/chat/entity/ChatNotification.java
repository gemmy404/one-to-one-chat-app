package com.app.chat.entity;

public record ChatNotification(
        String id,
        String senderId,
        String recipientId,
        String content
) {
}
