package com.shop.mall;

import com.shop.dto.MallDTO;
import com.shop.service.MallService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SelectOneTests {
    @Autowired
    MallService mallService;
    // @Test
    void contextLoads() {
        try {
            MallDTO mallDTO = mallService.get(102);
            System.out.println("mallDTO = " + mallDTO);
        } catch (Exception e) {
            System.out.println("SelectOneTests.contextLoads: 실패 ㅠㅠ");
        }

    }
}
