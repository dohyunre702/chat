package com.ll.example.app_2022_12_19.controller;

import com.ll.example.app_2022_12_19.ChatMessage;
import com.ll.example.app_2022_12_19.RsData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatController {
    private List<ChatMessage> chatMessages = new ArrayList<>();


    @PostMapping("/writeMessage")
    @ResponseBody
    public RsData<ChatMessage> writeMessage() {
        ChatMessage message = new ChatMessage("홍길동", "안녕하세요");

        return new RsData(
                "S-1",
                "메세지가 작성되었습니다.",
                null);
    }

}
