package com.shop.cust;

import com.shop.dto.CustDTO;
import com.shop.dto.ItemDTO;
import com.shop.service.CustService;
import com.shop.service.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class UpdateTests {
    @Autowired
    ItemService itemService;

    @Test
    void contextLoads() {
        try {
            ItemDTO itemDTO = itemService.get(105);
            ItemDTO newItemDTO = new ItemDTO(
                    itemDTO.getId(),
                    "item2",
                    20,
                    "2.omg",
                    LocalDateTime.now()
            );
            itemService.modify(
                    newItemDTO
            );
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("CustInsertTests.contextLoads/modify");
            throw new RuntimeException(e);
        }
    }
}
