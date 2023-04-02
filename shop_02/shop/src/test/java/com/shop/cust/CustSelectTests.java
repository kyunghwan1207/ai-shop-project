package com.shop.cust;
import com.shop.dto.CustDTO;
import com.shop.service.CustService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustSelectTests {
    @Autowired
    CustService custService;

    @Test
    void contextLoads() {
        CustDTO custDTO1 = null;
        CustDTO custDTO2 = new CustDTO("ko1", "1234", "고경환1");
        try {
            custService.register(custDTO2);
            custDTO1 = custService.get("ko1");
            System.out.println("custDTO1 = " + custDTO1);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("CustInsertTests.contextLoads/select오류");
            throw new RuntimeException(e);
        }
    }
}
