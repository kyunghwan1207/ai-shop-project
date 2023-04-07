package com.shop.ncp;

import com.shop.util.ChatBotUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class ChatUtilTests {

    @Test
    void contextLoads() throws IOException {
        String sendTxt = "니이름이뭐니";
        String replyTxt = ChatBotUtil.chat(sendTxt);
        System.out.println("replyTxt = " + replyTxt);
    }
}
