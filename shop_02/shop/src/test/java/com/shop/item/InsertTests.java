package com.shop.item;

import com.shop.dto.ItemDTO;
import com.shop.service.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class InsertTests {
    @Autowired
    ItemService itemService;

    @Test
    void contextLoads() {
        ItemDTO itemDTO1 = new ItemDTO(
                "아이템2",
                2000,
                "2.png"
        );
        try {
            itemService.register(itemDTO1);
            List<ItemDTO> itemDTOS = itemService.get();
            for (ItemDTO itemDTO : itemDTOS) {
                System.out.println("itemDTO = " + itemDTO);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
