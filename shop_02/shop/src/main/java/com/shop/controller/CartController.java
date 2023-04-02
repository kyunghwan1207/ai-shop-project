package com.shop.controller;

import com.shop.dto.CartDTO;
import com.shop.dto.CustDTO;
import com.shop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    String dir = "/cart/";
    @Autowired
    private CartService cartService;
    @GetMapping("")
    public String cartsAll(Model model, String id) throws Exception {
// CartDTO(id=121, cust_id=srlee, item_id=110, cnt=10, rdate=2023-03-30T00:00, item_name=화이트하임, cust_name=이세림, item_price=5000, item_imgname=img.png)
        System.out.println("id = " + id);
        List<CartDTO> cartDTOs = cartService.findAllWithCustId(id);
        for (CartDTO cartDTO : cartDTOs) {
            System.out.println("cartDTO = " + cartDTO);
        }
        model.addAttribute("cartDTOs", cartDTOs);
        model.addAttribute("left", dir + "left");
        model.addAttribute("center", dir + "center");
        return "main";
    }
    @GetMapping("/get")
    public String getALL(Model model) throws Exception {
        List<CartDTO> cartDTOS = cartService.get();
        model.addAttribute("cartDTOs", cartDTOS);
        model.addAttribute("left", dir + "left");
        model.addAttribute("center", dir + "center");
        return "main";
    }
    @GetMapping("/my-cart")
    public String cartsAllWithCustDTO(Model model, HttpSession session) throws UserPrincipalNotFoundException {
        CustDTO sessionCustDTO = getCustDTO(session);
        String custId = sessionCustDTO.getId();
        return "main";
    }
    @RequestMapping("/addimpl") // from item/getDetail.html
    public String addImpl(CartDTO cartDTO, Model model) {
        System.out.println("addimpl / cartDTO = " + cartDTO);

        model.addAttribute("left", dir + "left");
        model.addAttribute("center", dir + "center");
        return "main";
    }
    @GetMapping("/add")
    public String addGET(CartDTO cartDTO, Model model, HttpSession session) throws UserPrincipalNotFoundException {
        CustDTO sessionCustDTO = getCustDTO(session);
        model.addAttribute("custId", sessionCustDTO.getId());
        model.addAttribute("custName", sessionCustDTO.getName());
        model.addAttribute("left", dir + "left");
        model.addAttribute("center", dir + "center");
        return "main";
    }

    @PostMapping("/add") // from cart/add.html
    public String addPOST(CartDTO cartDTO, Model model, HttpSession session) throws Exception {
        System.out.println("in addPOST / cartDTO = " + cartDTO);
        CustDTO sessionCustDTO = getCustDTO(session);
        CartDTO newCartDTO = new CartDTO(0, sessionCustDTO.getId(), cartDTO.getItem_id(), cartDTO.getCnt(), LocalDateTime.now());
        cartService.register(newCartDTO);
        model.addAttribute("left", dir+"left");
        model.addAttribute("center", dir+"center");
        return "main";
    }
    private CustDTO getCustDTO(HttpSession session) throws UserPrincipalNotFoundException {
        CustDTO retDTO = null;
        if (session.getAttribute("custDTO") instanceof CustDTO) {
            retDTO = (CustDTO) session.getAttribute("custDTO");
        } else {
            throw new UserPrincipalNotFoundException("사용자를 찾을 수 없습니다.");
        }
        return retDTO;
    }
}
