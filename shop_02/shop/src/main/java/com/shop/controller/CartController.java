package com.shop.controller;

import com.github.pagehelper.PageInfo;
import com.shop.dto.CartDTO;
import com.shop.dto.CustDTO;
import com.shop.service.CartService;
import com.shop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @Autowired
    private ItemService itemService;
    @GetMapping("")
    public String cartsAll(
            @RequestParam(required = false, defaultValue = "1") int pageNum,
            Model model,
            String id) throws Exception {
        System.out.println("/cart(id=" + id + "| CartController.cartsAll");
// CartDTO(id=121, cust_id=srlee, item_id=110, cnt=10, rdate=2023-03-30T00:00, item_name=화이트하임, cust_name=이세림, item_price=5000, item_imgname=img.png)
        PageInfo<CartDTO> p = new PageInfo<>(cartService.getPageWithCustId(pageNum, id), 10);
        System.out.println("p = " + p);
        List<CartDTO> carts = p.getList();
        for (CartDTO cart : carts) {
            System.out.println("cart = " + cart);
        }
        model.addAttribute("cartDTOs", p);
        model.addAttribute("left", dir + "left");
        model.addAttribute("center", dir + "get");
        return "main";
    }
    @GetMapping("/get")
    public String getALL(Model model) throws Exception {
        System.out.println("/cart/get | CartController.getALL");
        List<CartDTO> cartDTOs = cartService.get();
        for (CartDTO cartDTO : cartDTOs) {
            System.out.println("cartDTO = " + cartDTO);
        }
        model.addAttribute("cartDTOs", cartDTOs);
        model.addAttribute("left", dir + "left");
        model.addAttribute("center", dir + "center");
        return "main";
    }
    @GetMapping("/my-cart")
    public String cartsAllWithCustDTO(Model model, HttpSession session) throws UserPrincipalNotFoundException {
        System.out.println("/cart/my-cart | CartController.cartsAllWithCustDTO");
        CustDTO sessionCustDTO = getCustDTO(session);
        String custId = sessionCustDTO.getId();
        return "main";
    }
    @RequestMapping("/addimpl") // from item/getDetail.html
    public String addImpl(CartDTO cartDTO, Model model) {
        System.out.println("/cart/addimpl | cartDTO = " + cartDTO);

        model.addAttribute("left", dir + "left");
        model.addAttribute("center", dir + "center");
        return "main";
    }
    @GetMapping("/add")
    public String addGET(CartDTO cartDTO, Model model, HttpSession session) throws UserPrincipalNotFoundException {
        System.out.println("[GET] /cart/add| CartController.addGET");
        CustDTO sessionCustDTO = getCustDTO(session);
        List<Integer> itemIdList = itemService.getIdList();
        model.addAttribute("itemIdList", itemIdList);
        model.addAttribute("custId", sessionCustDTO.getId());
        model.addAttribute("custName", sessionCustDTO.getName());
        model.addAttribute("left", dir + "left");
        model.addAttribute("center", dir + "add");
        return "main";
    }

    @PostMapping("/add") // from cart/add.html
    public String addPOST(CartDTO cartDTO, Model model, HttpSession session) throws Exception {
        System.out.println("[POST] /cart/add | CartController.addPOST");
        System.out.println("in addPOST / cartDTO = " + cartDTO);
        CustDTO sessionCustDTO = getCustDTO(session);
        CartDTO newCartDTO = new CartDTO(0, sessionCustDTO.getId(), cartDTO.getItem_id(), cartDTO.getCnt(), LocalDateTime.now());
        cartService.register(newCartDTO);
        model.addAttribute("left", dir+"left");
        model.addAttribute("center", dir+"center");
        return "main";
    }
    @GetMapping("/getpage")
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
