package com.shop.controller;

import com.github.pagehelper.PageInfo;
import com.shop.dto.MallDTO;
import com.shop.service.MallService;
import com.shop.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value(value = "${imglocation}")
    String custdir;

    @GetMapping("")
    public String mall(Model model, @RequestParam(value = "searchName", required = false) String searchName) {
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
    @RequestMapping("/addimpl")
    public String mallAdd(MallDTO mallDTO, Model model) {
        System.out.println("mallDTO = " + mallDTO);
        try {
            FileUploadUtil.saveFile(mallDTO.getImg());
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
    @GetMapping("/detail")
    public String itemDetail(Model model, int id) throws Exception {
        System.out.println("id = " + id);
        MallDTO mallDTO = mallService.get(id);
        model.addAttribute("left", dir+"left");
        model.addAttribute("center", dir+"detail");
        model.addAttribute("mallDTO", mallDTO);
        return "main";
    }

    @GetMapping("/getpage")
    public String getpage(@RequestParam(required = false, defaultValue = "1") int pageNum, Model model) throws Exception {
        System.out.println("[GET] /cust/getpage | CustController.getpage");
        PageInfo<MallDTO> p = new PageInfo<>(mallService.getPage(pageNum), 10);
        System.out.println("p = " + p);
        model.addAttribute("malls", p);
        model.addAttribute("left", dir+"left");
        model.addAttribute("center", dir+"getpage");
        return "main";
    }
    @GetMapping("/delete")
    public String mallDelete(Model model, int id) throws Exception {
        mallService.remove(id);
        model.addAttribute("left", dir + "left");
        model.addAttribute("center", dir + "get");
        return "main";
    }

    @PostMapping("/update") // {id, name, address, phonenumber, ownername, imagename(기존의 이미지이름), img(새로운 이미지 있을 수도 있고, 없을 수도 있음)}
    public String mallUpdate(MallDTO mallDTO, Model model) throws Exception {
        if (mallDTO.getImg().getOriginalFilename().isEmpty()) {
            System.out.println("새로운 이미지가 없습니다.");
            mallService.modify(mallDTO); // 기존 imgname 으로 업데이트 된다.
        } else {
            String newImgName = mallDTO.getImg().getOriginalFilename();
            FileUploadUtil.saveFile(mallDTO.getImg());
            mallDTO.setImgname(newImgName);
            mallService.modify(mallDTO);
        }

        return "redirect:/mall/detail?id=" + mallDTO.getId();
    }
    @GetMapping("/map")
    public String mallMap(Model model) {
        model.addAttribute("appkey", "52c9e8620c1081572383163cce5ffbb9");
        model.addAttribute("left", dir+"left");
        model.addAttribute("center", dir+"map");
        return "main";
    }
}
