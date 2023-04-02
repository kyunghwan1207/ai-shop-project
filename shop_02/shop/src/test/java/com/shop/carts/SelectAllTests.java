package com.shop.carts;

import com.shop.dto.CartDTO;
import com.shop.service.CartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SelectAllTests {
    @Autowired
    CartService cartService;

    @Test
    void contextLoads() {
        try {
            List<CartDTO> cartDTOS = cartService.get();
            for (CartDTO cartDTO : cartDTOS) {
                System.out.println("cartDTO = " + cartDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
