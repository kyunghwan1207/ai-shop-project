package com.shop.controller;

import com.shop.dto.MsgDTO;
import com.shop.util.ChatBotUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;


@Controller
public class MsgController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/receiveall") // 모두에게 전송
    public void receiveall(MsgDTO msg, SimpMessageHeaderAccessor headerAccessor) {
        System.out.println("receiveall() / msg = " + msg);
        simpMessagingTemplate.convertAndSend("/send", msg);
    }
    @MessageMapping("/receiveme") // 나에게만 전송 ex)Chatbot
    public void receiveme(MsgDTO msg, SimpMessageHeaderAccessor headerAccessor) {
        System.out.println("receiveme() / msg = " + msg);
        String id = msg.getSendid();
        msg.setContent2("TR Message");
        simpMessagingTemplate.convertAndSend("/send/"+id, msg);
    }
    @MessageMapping("/receiveto") // 특정 Id에게 전송
    public void receiveto(MsgDTO msg, SimpMessageHeaderAccessor headerAccessor) {
        System.out.println("receiveto() / msg = " + msg);
        String id = msg.getSendid();
        String target = msg.getReceiveid();
        simpMessagingTemplate.convertAndSend("/send/to/"+target, msg);
    }

    @MessageMapping("/chatbotme") // 특정 Id에게 전송
    public void chatbotme(MsgDTO msg, SimpMessageHeaderAccessor headerAccessor) throws IOException {
        System.out.println("chatbotme() / msg = " + msg);
        System.out.println("msg.getContent1() = " + msg.getContent1());

        String id = msg.getSendid();
        String target = msg.getReceiveid();
        String txt = msg.getContent1();
        String result = ChatBotUtil.chat(txt);
        System.out.println("result = " + result);
        msg.setContent1(result);
        msg.setReceiveid(target);
        simpMessagingTemplate.convertAndSend("/send/to/" + target, msg);
    }

    @GetMapping("/chat")
    public String chat(Model model) {
        model.addAttribute("center", "chat/chat");
        return "main";
    }
    @GetMapping("/broadcast")
    public String broadcast(Model model) {
        model.addAttribute("center", "chat/broadcast");
        return "main";
    }
    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("center", "chat/admin");
        return "main";
    }
    @GetMapping("/chatbot")
    public String chatbot(Model model) {
        model.addAttribute("center", "chat/chatbot");
        return "main";
    }
}
