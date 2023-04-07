package com.shop.controller;

import com.github.pagehelper.PageInfo;
import com.shop.dto.ItemDTO;
import com.shop.service.ItemService;
import com.shop.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ItemService itemService;
    String dir = "/item/";

    @Value(value = "${imglocation}")
    String custdir;

    @GetMapping("")
    public String item(Model model) {
        // left와 center 영역만 바꿔보자!
        model.addAttribute("left", dir+"left");
        model.addAttribute("center", dir+"center");
        return "main";
    }
    @PostMapping("/add")
    public String itemAdd(ItemDTO itemDTO, Model model) {
        String imgName = itemDTO.getImg().getOriginalFilename();
        System.out.println("imgName = " + imgName);
        itemDTO.setImgname(imgName);
        itemDTO.setRdate(LocalDateTime.now());

        try {
            FileUploadUtil.saveFile(itemDTO.getImg());
            itemService.register(itemDTO);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            System.out.println("fail to itemAdd() :(");
        }

        model.addAttribute("left", dir+"left");
        model.addAttribute("center", dir+"add");

        return "redirect:/item/getpage";
    }
    @GetMapping("/add")
    public String itemAddGET(ItemDTO itemDTO, Model model) {
        model.addAttribute("itemDTO", itemDTO);
        model.addAttribute("left", dir+"left");
        model.addAttribute("center", dir+"add");

        return "main";
    }

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
        model.addAttribute("left", dir+"left");
        model.addAttribute("center", dir+"detail");
        model.addAttribute("itemDTO", itemDTO);
        return "main";
    }

    @GetMapping("/getpage")
    public String getpage(@RequestParam(required = false, defaultValue = "1") int pageNum, Model model) throws Exception {
        System.out.println("[GET] /cust/getpage | CustController.getpage");
        PageInfo<ItemDTO> p = new PageInfo<>(itemService.getPage(pageNum), 10);
        System.out.println("p = " + p);
        model.addAttribute("items", p);
        model.addAttribute("left", dir+"left");
        model.addAttribute("center", dir+"getpage");
        return "main";
    }

    @PostMapping("/update") // {id, name, price, imagename(기존의 이미지이름), img(새로운 이미지 있을 수도 있고, 없을 수도 있음)}
    public String itemUpdate(ItemDTO itemDTO, Model model) throws Exception {
        System.out.println("itemDTO.getImg()='" + itemDTO.getImg() + "'");
        String originalFilename = itemDTO.getImg().getOriginalFilename();
        System.out.println("originalFilename='" + originalFilename + "'");
        if (itemDTO.getImg().getOriginalFilename().isEmpty()) {
            System.out.println("새로운 이미지가 없습니다.");
            itemService.modify(itemDTO); // 기존 imgname 으로 업데이트 된다.
        } else {
            String newImgName = itemDTO.getImg().getOriginalFilename();
            FileUploadUtil.saveFile(itemDTO.getImg());
            itemDTO.setImgname(newImgName);
            itemService.modify(itemDTO);
        }

        return "redirect:/item/detail?id=" + itemDTO.getId();
    }
    @GetMapping("/delete")
    public String deleteItem(int id) throws Exception {
        itemService.remove(id);
        return "redirect:/item/getpage";
    }

    @GetMapping("/addcart")
    public String itemAddCart(Model model, int id) throws Exception {
        System.out.println("id = " + id);
        ItemDTO itemDTO = itemService.get(id);
        model.addAttribute("left", dir+"left");
        model.addAttribute("center", dir+"addcart");
        model.addAttribute("itemDTO", itemDTO);
        return "main";
    }
}
