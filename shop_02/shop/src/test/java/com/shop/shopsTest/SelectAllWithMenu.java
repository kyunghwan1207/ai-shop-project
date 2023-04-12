package com.shop.shopsTest;

import com.shop.dto.CartDTO;
import com.shop.dto.ShopDTO;
import com.shop.service.CartService;
import com.shop.service.ShopService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SelectAllWithMenu {
    @Autowired
    ShopService shopService;

    // @Test
    void contextLoads() {
        try {
            List<ShopDTO> shopDTOS = shopService.getWithMenu("S001");
            for (ShopDTO shopDTO : shopDTOS) {
                System.out.println("shopDTO = " + shopDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
