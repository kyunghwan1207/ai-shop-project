package com.shop.ncp;

import com.shop.util.WeatherUtil;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest
class WeatherTests {

	@Test
	void contextLoads() throws IOException, ParseException {

		String result = WeatherUtil.getWeatherInfoWhichLoc("159");
		System.out.println("result = " + result);
	}

}
