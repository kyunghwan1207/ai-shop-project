package com.shop.mall;

import com.shop.dto.MallDTO;
import com.shop.service.MallService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InsertTests {
    @Autowired
    MallService mallService;

    @Test
    void contextLoads() {
        // 부산광역시 해운대구 센텀1로 17
        // 이마트24 센텀프리미어호텔점
        // 02-6919-1500
        // LocalDateTime.now()
        // 고경환
        // imgname: img

//        MallDTO mallDTO = new MallDTO(
//
//        );
//        mallService.register();
    }
}
