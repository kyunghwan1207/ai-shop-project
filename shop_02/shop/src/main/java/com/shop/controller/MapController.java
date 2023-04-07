package com.shop.controller;

import com.shop.dto.CustDTO;
import com.shop.dto.ShopDTO;
import com.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MapController {

    @Autowired
    ShopService shopService;

    @RequestMapping("/map1")
    public String map1(Model model) {
        model.addAttribute("center", "map1");
        return "main";
    }

    @RequestMapping("/map2")
    public String map2(Model model) {
        model.addAttribute("center", "map2");
        return "main";
    }

    @RequestMapping("/shopdetail")
    public String shopdetail(Model model, String id) {
        System.out.println("shopdetail() with / id = " + id);
        ShopDTO shopDTO = null;
        try {
            List<ShopDTO> shopDTOS = shopService.getWithMenu(id); // id는 매장 id (shop_id)
            model.addAttribute("ditem", shopDTOS.get(0));
            model.addAttribute("menus", shopDTOS);
            model.addAttribute("shopid", id);

            for (ShopDTO dto : shopDTOS) {
                System.out.println("dto = " + dto);
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        model.addAttribute("center", "shopdetail");

        return "main";
    }
}

