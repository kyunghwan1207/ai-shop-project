package com.shop.carts;

import com.shop.dto.CartDTO;
import com.shop.service.CartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SelectOneTests {
    @Autowired
    CartService cartService;

    @Test
    void contextLoads() {
        try {
            CartDTO cartDTO = cartService.get(117);
            System.out.println("cartDTO = " + cartDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
