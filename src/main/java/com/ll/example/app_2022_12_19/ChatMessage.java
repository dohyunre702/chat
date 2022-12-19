package com.ll.example.app_2022_12_19;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ChatMessage {
    private long id;
    private LocalDateTime createDate;
    private String authorName;
    private String content;

    public ChatMessage(String authorName, String content) {
        this(1, LocalDateTime.now(), authorName, content);
    }
}

class ChatMessageIdGenerator {
    private static long id = 0;

    public static long genNextId() {
        return ++id;
    }
}
