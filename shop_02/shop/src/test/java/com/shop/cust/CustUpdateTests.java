package com.shop.cust;

import com.shop.dto.CustDTO;
import com.shop.service.CustService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustUpdateTests {
    @Autowired
    CustService custService;

    @Test
    void contextLoads() {
//        CustDTO custDTO1 = new CustDTO("ko1", "1234", "고경환1");
        CustDTO custDTO2 = new CustDTO("ko1", "12345", "고경환11");
        try {
            custService.modify(custDTO2);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("CustInsertTests.contextLoads/modify");
            throw new RuntimeException(e);
        }
    }
}
