package com.shop.carts;

import com.shop.dto.CartDTO;
import com.shop.dto.ItemDTO;
import com.shop.service.CartService;
import com.shop.service.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class InsertTests {
    @Autowired
    CartService cartService;

    // @Test
    void contextLoads() {
        CartDTO cartDTO = new CartDTO(0, "jina", 112, 32, new Date());

        try {
            cartService.register(cartDTO);
            CartDTO cartDTO1 = cartService.get(cartDTO.getId());
            System.out.println("cartDTO1 = " + cartDTO1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
