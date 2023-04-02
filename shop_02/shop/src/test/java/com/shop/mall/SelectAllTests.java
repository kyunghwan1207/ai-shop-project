package com.shop.mall;

import com.shop.dto.MallDTO;
import com.shop.service.MallService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SelectAllTests {
    @Autowired
    MallService mallService;
    @Test
    void contextLoads() {
        try {
            List<MallDTO> mallDTOS = mallService.get();
            for (MallDTO mallDTO : mallDTOS) {
                System.out.println("mallDTO = " + mallDTO);
            }
        } catch (Exception e) {
            System.out.println("SelectAllTests.contextLoads fail ㅜㅜ");
            throw new RuntimeException(e);
        }
    }
}
