package com.shop.item;

import com.shop.ShopApplication;
import com.shop.dto.ItemDTO;
import com.shop.service.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ShopApplication.class)
public class SelectTests {
    @Autowired
    ItemService itemService;

    // @Test
    void contextLoads() throws Exception {
        ItemDTO itemDTO = itemService.get(1);
        System.out.println("itemDTO = " + itemDTO);
    }
}
