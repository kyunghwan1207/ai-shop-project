package com.shop.ncp;

import com.github.pagehelper.PageException;
import com.shop.util.CFRCUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class CFRCTests {

    @Test
    void contextLoads() throws IOException, ParseException {
        String result = CFRCUtil.getText("mableey01.jpg");
        JSONParser jsonParser = new JSONParser();
        JSONObject globalResult = (JSONObject) jsonParser.parse(result);
        JSONArray faces = (JSONArray) globalResult.get("faces");
        JSONObject jo1 = (JSONObject) faces.get(0);
        JSONObject celebrity = (JSONObject) jo1.get("celebrity");
        String celName = (String) celebrity.get("value");

        System.out.println("celName = " + celName);
    }
}
