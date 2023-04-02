package com.shop.controller;

import com.shop.dto.ItemDTO;
import com.shop.dto.MallDTO;
import com.shop.service.ItemService;
import com.shop.service.MallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/mall")
public class MallController {
    @Autowired
    MallService mallService;
    String dir = "/mall/";
    @GetMapping("")
    public String mall(Model model) {
        // left와 center 영역만 바꿔보자!
        model.addAttribute("left", dir+"left");
        model.addAttribute("center", dir+"center");
        return "main";
    }
    @RequestMapping("/addimpl")
    public String mallAdd(MallDTO mallDTO, Model model) {
        System.out.println("mallDTO = " + mallDTO);

        try {
            mallDTO.setRdate(LocalDateTime.now());
            mallService.register(mallDTO);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("fail to mallAdd() :(");
        }

        model.addAttribute("left", dir+"left");
        model.addAttribute("center", dir+"add");

        return "main";
    }
    @RequestMapping("/add")
    public String mallAddGET(MallDTO mallDTO, Model model) {
        model.addAttribute("mallDTO", mallDTO);
        model.addAttribute("left", dir+"left");
        model.addAttribute("center", dir+"add");

        return "main";
    }

    @GetMapping("/get")
    public String mallGetAll(Model model, @RequestParam(value = "searchName", required = false) String searchName) {
        List<MallDTO> mallDTOs = null;
        if (searchName != null) {
            // 이름으로 상품 검색하는 로직
        }
        try {
            mallDTOs = mallService.get();
            for (MallDTO mallDTO : mallDTOs) {
                System.out.println("mallDTO = " + mallDTO);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("left", dir+"left");
        model.addAttribute("center", dir+"get");
        model.addAttribute("mallDTOs", mallDTOs);
        return "main";
    }

}
