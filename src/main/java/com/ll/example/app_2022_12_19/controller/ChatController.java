package com.ll.example.app_2022_12_19.controller;

import com.ll.example.app_2022_12_19.ChatMessage;
import com.ll.example.app_2022_12_19.RsData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/chat")
public class ChatController {
    private List<ChatMessage> chatMessages = new ArrayList<>();

    public record WriteMessageRequest(String authorName, String content) {
    }
    public record WriteMessageResponse(long id) { //jdk 16+
    }

    @PostMapping("/writeMessage")
    @ResponseBody
    public RsData<WriteMessageResponse> writeMessage(@RequestBody WriteMessageRequest req) {
        ChatMessage message = new ChatMessage(req.authorName(), req.content());

        chatMessages.add(message);

        return new RsData<>(
                "S-1",
                "메세지가 작성되었습니다.",
                new WriteMessageResponse(message.getId()));
    }

    public record MessagesRequest(@RequestParam(defaultValue = "0") Long fromId) {
    }
    public record MessagesResponse(List<ChatMessage> messages, long count) {
    }

    @GetMapping("/messages") //메세지 가져오기
    @ResponseBody
    public RsData<MessagesResponse> messages(MessagesRequest req) {
        List<ChatMessage> messages = chatMessages;

        //번호가 입력되었다면
        if(req.fromId != null) {
            //반복문으로 할 수 있지만 intStream이 멀티쓰레드로 바꾸기 쉽다
            //해당 번호의 채팅메시지가 전체 리스트에서의 배열인덱스 번호를 구한다
            //없다면 -1
            int index = IntStream.range(0, messages.size())
                    .filter(i -> chatMessages.get(i).getId() == req.fromId)
                    .findFirst()
                    .orElse(-1);

            /*
            int foundIndex = -1;
            for(int i = 0; i < messages.size(); i++ {
                if(messages.get(i).getId() == req.fromId) {
                    foundIndex = i;
                    break;
                }
             */

            //만약 index가 있다면 0번 인덱스부터 index 번까지 제거한 리스트를 반환
            if(index != -1) {
                messages = messages.subList(index + 1, messages.size());
            }
        }
        return new RsData<>(
                "S-1",
                "성공",
                new MessagesResponse(chatMessages, chatMessages.size()));
    }
}
