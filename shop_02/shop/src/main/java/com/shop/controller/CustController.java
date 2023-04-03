package com.shop.controller;

import com.github.pagehelper.PageInfo;
import com.shop.dto.CustDTO;
import com.shop.service.CustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@Controller
@RequestMapping("/cust")
public class CustController {
    @Autowired
    CustService custService;
    String dir = "/cust/";
    @GetMapping("")
    public String cust(Model model) {
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

//        return "redirect:/cust/add";
        return "redirect:/cust/getpage";
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

    @GetMapping("/getpage")
    public String getpage(@RequestParam(required = false, defaultValue = "1") int pageNum, Model model) throws Exception {
        System.out.println("[GET] /cust/getpage | CustController.getpage");
        PageInfo<CustDTO> p = new PageInfo<>(custService.getPage(pageNum), 10);
        System.out.println("p = " + p);
        model.addAttribute("custs", p);
        model.addAttribute("left", dir+"left");
        model.addAttribute("center", dir+"getpage");
        return "/main";
    }
    @GetMapping("/detail")
    public String getCustDetail(Model model, String id) throws UserPrincipalNotFoundException {
        System.out.println("[GET] /cust/detail | id = " + id);
        CustDTO custDTO = null;
        try {
            custDTO = custService.get(id);
            model.addAttribute("custDTO", custDTO);
            System.out.println("custDTO = " + custDTO);
        } catch (Exception e) {
            throw new UserPrincipalNotFoundException("사용자를 찾을 수 없습니다.");
        }
        model.addAttribute("left", dir + "left");
        model.addAttribute("center", dir + "detail");
        return "main";
    }
    @PostMapping("/updateimpl")
    public String updateImpl(Model model, CustDTO custDTO) {
        System.out.println("/cust/updateimpl | model = " + model);
        System.out.println("/cust/updateimpl | custDTO = " + custDTO);
        try {
            custService.modify(custDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "redirect:/cust/detail?id=" + custDTO.getId();
    }
    @GetMapping("/deleteimpl")
    public String deleteImpl(Model model, String id) {
        System.out.println("/deleteimpl | id = " + id);
        try {
            custService.remove(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "redirect:/cust/getpage";
    }

}
