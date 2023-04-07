package com.shop.mall;

import com.shop.dto.MallDTO;
import com.shop.service.MallService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class InsertTests {
    @Autowired
    MallService mallService;

    @Test
    void contextLoads() {
        MallDTO obj = new MallDTO(0,"독일깁밥","부산광역시 수영구 지하상가","kimbab.jpg", LocalDateTime.now(),"고경환","051-756-2347");
        try {
            mallService.register(obj);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("입력시 오류");
        }
    }
}
