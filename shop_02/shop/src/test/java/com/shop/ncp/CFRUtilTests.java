package com.shop.ncp;

import com.shop.util.CFRUtil;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Map;

@SpringBootTest
public class CFRUtilTests {

    @Test
    void contextLoads() throws IOException, ParseException {
        String result = CFRUtil.getText("mableey01.jpg");
        System.out.println("result = " + result);
        Map<String, String> faceInfoMap = CFRUtil.getFaceInfoFromResult(result);

        System.out.println("emotion = " + faceInfoMap.get("emotion"));
        System.out.println("gender = " + faceInfoMap.get("gender"));
        System.out.println("pose = " + faceInfoMap.get("pose"));
        System.out.println("age = " + faceInfoMap.get("age"));

    }
}
