package com.shop.carts;

import com.shop.dto.CartDTO;
import com.shop.service.CartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class UpdateTests {
    @Autowired
    CartService cartService;

    @Test
    void contextLoads() {
        try {
            CartDTO modifyedCartDto = new CartDTO(
                    117, null, 109, 50, LocalDateTime.now()
            );
            cartService.modify(modifyedCartDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
