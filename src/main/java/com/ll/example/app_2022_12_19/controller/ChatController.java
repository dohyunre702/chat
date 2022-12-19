package com.ll.example.app_2022_12_19.controller;

import com.ll.example.app_2022_12_19.ChatMessage;
import com.ll.example.app_2022_12_19.RsData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatController {
    private List<ChatMessage> chatMessages = new ArrayList<>();
    public static class WriteMessgaeRequest() {
        private final String authorName;
        private final String content;
    }

    public record WriteMessageResponse(long id) { //jdk 16+
    }

    @PostMapping("/writeMessage")
    @ResponseBody
    public RsData<WriteMessageResponse> writeMessage() {
        ChatMessage message = new ChatMessage("홍길동", "안녕하세요");

        chatMessages.add(message);

        return new RsData<>(
                "S-1",
                "메세지가 작성되었습니다.",
                new WriteMessageResponse(message.getId()));
    }

    @GetMapping("/messages") //메세지 가져오기
    @ResponseBody
    public RsData<List<ChatMessage>> messages() {
        return new RsData<>(
                "S-1",
                "성공",
                chatMessages);
    }

}
