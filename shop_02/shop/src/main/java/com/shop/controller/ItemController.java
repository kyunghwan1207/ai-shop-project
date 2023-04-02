package com.shop.controller;

import com.shop.dto.CustDTO;
import com.shop.dto.ItemDTO;
import com.shop.service.CustService;
import com.shop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    ItemService itemService;
    String dir = "/item/";
    @GetMapping("")
    public String item(Model model) {
        // left와 center 영역만 바꿔보자!
        model.addAttribute("left", dir+"left");
        model.addAttribute("center", dir+"center");
        return "main";
    }
    @PostMapping("/add")
    public String itemAdd(ItemDTO itemDTO, Model model) {
        System.out.println("in itemAdd() / itemDTO = " + itemDTO);

        try {
            itemService.register(itemDTO);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("fail to itemAdd() :(");
        }

        model.addAttribute("left", dir+"left");
        model.addAttribute("center", dir+"add");

        return "main";
    }
    @GetMapping("/add")
    public String itemAddGET(ItemDTO itemDTO, Model model) {
        model.addAttribute("itemDTO", itemDTO);
        model.addAttribute("left", dir+"left");
        model.addAttribute("center", dir+"add");

        return "main";
    }

//    @RequestMapping("/get/${custId}")
//    public String custGet(Model model, @PathVariable String custId) {
//        System.out.println("custId = " + custId);
//        try {
//            CustDTO findCustDTO = custService.get(custId);
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("CustController.custGet / fail to custGet() :(");
//        }
//
//        model.addAttribute("left", dir+"left");
//        model.addAttribute("center", dir+"get");
//        return "main";
//    }
    @GetMapping("/get")
    public String itemGetAll(Model model, @RequestParam(value = "searchName", required = false) String searchName) {
        List<ItemDTO> itemDTOs = null;
        if (searchName != null) {
            // 이름으로 상품 검색하는 로직
        }
        try {
            itemDTOs = itemService.get();
            for (ItemDTO itemDTO : itemDTOs) {
                System.out.println("itemDTO = " + itemDTO);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("left", dir+"left");
        model.addAttribute("center", dir+"get");
        model.addAttribute("itemDTOs", itemDTOs);
        return "main";
    }
    @GetMapping("/detail")
    public String itemDetail(Model model, int id) throws Exception {
        System.out.println("id = " + id);
        ItemDTO itemDTO = itemService.get(id);
        model.addAttribute("center", dir+"detail");
        model.addAttribute("left", dir+"left");
        model.addAttribute("itemDTO", itemDTO);
        return "main";
    }

}
