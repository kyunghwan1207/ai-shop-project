package com.shop.controller;

import com.shop.dto.CustDTO;
import com.shop.service.CustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cust")
public class CustController {
    @Autowired
    CustService custService;
    String dir = "/cust/";
    @GetMapping("")
    public String cust(Model model) {
        // left와 center 영역만 바꿔보자!
        model.addAttribute("left", dir+"left");
        model.addAttribute("center", dir+"center");
        return "main";
    }
    @PostMapping("/add")
    public String custAdd(CustDTO custDTO) {
        System.out.println("in custAdd() / custDTO = " + custDTO);
        try {
            custService.register(custDTO);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("fail to custAdd() :(");
        }

        return "redirect:/cust/add";
    }

    @GetMapping("/add")
    public String custAddGet(CustDTO custDTO, Model model) {
        model.addAttribute("custDTO", custDTO);
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
    public String custGetAll(Model model, @RequestParam(value = "searchName", required = false) String searchName) throws Exception {
        System.out.println("CustController.custGetAll");
        System.out.println("searchName = " + searchName);
        List<CustDTO> custDTOs = null;
        if (searchName != null) {
            // 회원 이름으로 회원 검색하는 로직
            custDTOs = custService.findByName(searchName);
        } else {
            try {
                custDTOs = custService.get();
                for (CustDTO custDTO : custDTOs) {
                    System.out.println("custDTO = " + custDTO);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        model.addAttribute("left", dir+"left");
        model.addAttribute("center", dir+"get");
        model.addAttribute("custDTOs", custDTOs);
        return "main";
    }

}
