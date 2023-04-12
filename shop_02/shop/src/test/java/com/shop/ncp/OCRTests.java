package com.shop.ncp;

import com.shop.util.OCRUtil;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

@SpringBootTest
class OCRTests {

	// @Test
	void contextLoads() throws ParseException, IOException {
		String imgname = "bl02.png";
		String result = OCRUtil.getText(imgname);
		System.out.println("result = " + result);

		JSONParser jsonparser = new JSONParser();
		JSONObject globalResult = (JSONObject)jsonparser.parse(result);

		JSONArray images = (JSONArray) globalResult.get("images"); // 배열
		JSONObject jo1 = (JSONObject) images.get(0);

		JSONArray fields = (JSONArray) jo1.get("fields");
		JSONObject obj = (JSONObject) fields.get(0);
		String name = (String)obj.get("inferText");
		String keyName = (String) obj.get("name");
		System.out.println(keyName + ": " + name);


		JSONObject title = (JSONObject) jo1.get("title");
		String num = (String)title.get("inferText");
		String keyName2 = (String) title.get("name");
		System.out.println(keyName2 + ": " + num);
	}

}
