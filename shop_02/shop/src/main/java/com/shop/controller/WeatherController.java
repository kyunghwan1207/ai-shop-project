package com.shop.controller;

import com.shop.util.WeatherUtil;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class WeatherController {
    @GetMapping("/weather")
    public String getWeather(Model model) {
        System.out.println("NCPController.getWeather");
        model.addAttribute("center", "weather");
        model.addAttribute("locName", "선택");
        model.addAttribute("weatherInfoDTO", null);
        return "main";
    }
    @PostMapping("/weather")
    public String weatherimpl(Model model, String locName) throws IOException, ParseException {
        System.out.println("weatherimple() / locName = " + locName);
        String temp = WeatherUtil.getWeatherInfoWhichLoc(locName);
        String weatherInfo =  locName + "지역의 날씨 정보입니다.\n" + temp;
        model.addAttribute("weatherInfoDTO", weatherInfo);
        model.addAttribute("locName", locName);
        model.addAttribute("center", "weatherimpl");
        return "main";
    }
}
