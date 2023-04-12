package com.shop.cust;
import com.shop.dto.CustDTO;
import com.shop.service.CustService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CustSelectAllTests {
    @Autowired
    CustService custService;

    // @Test
    void contextLoads() {
        List<CustDTO> list = null;
        try {
            List<CustDTO> custDTOS = custService.get();
            for (CustDTO custDTO : custDTOS) {
                System.out.println("custDTO = " + custDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("CustInsertTests.contextLoads/select오류");
            throw new RuntimeException(e);
        }
    }
}
