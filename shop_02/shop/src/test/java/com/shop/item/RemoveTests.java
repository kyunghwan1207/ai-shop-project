package com.shop.item;

import com.shop.dto.ItemDTO;
import com.shop.service.CustService;
import com.shop.service.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RemoveTests {
    @Autowired
    ItemService itemService;

    @Test
    void contextLoads() {
//        CustDTO custDTO1 = new CustDTO("ko1", "1234", "고경환1");

        try {
            itemService.remove(1);
            ItemDTO itemDTO1 = itemService.get(1);
            if (itemDTO1 == null) {
                System.out.println("Success to Remove!");
            } else {
                System.out.println("Fail to Remove :(");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("CustInsertTests.contextLoads/remove");
            throw new RuntimeException(e);
        }
    }
}
