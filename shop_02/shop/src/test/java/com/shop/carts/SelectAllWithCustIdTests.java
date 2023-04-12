package com.shop.carts;

import com.shop.dto.CartDTO;
import com.shop.service.CartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SelectAllWithCustIdTests {
    @Autowired
    CartService cartService;

    // @Test
    void contextLoads() {
        try {
            List<CartDTO> findCartDto = cartService.findAllWithCustId("ko1");
            System.out.println("findCartDto = " + findCartDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
