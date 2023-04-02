package com.shop.item;

import com.shop.ShopApplication;
import com.shop.dto.ItemDTO;
import com.shop.service.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = ShopApplication.class)
public class SearchTests {
    @Autowired
    ItemService itemService;
    @Test
    void contextLoads() {
        List<ItemDTO> itemDTOS;
        try {
            itemDTOS = itemService.search("초코");
            for (ItemDTO itemDTO : itemDTOS) {
                System.out.println("itemDTO = " + itemDTO);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
