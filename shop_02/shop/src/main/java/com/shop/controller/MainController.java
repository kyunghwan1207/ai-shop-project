package com.shop.controller;

import com.shop.dto.CheckDuplicateIdRequestDTO;
import com.shop.dto.CustDTO;
import com.shop.dto.FaceInfoDTO;
import com.shop.service.CustService;
import com.shop.util.CFRUtil;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    CustService custService;
    @GetMapping("/")
    public String hello(CustDTO custDTO, Model model) {
        model.addAttribute("custDTO", custDTO);
        return "main";
    }
    @PostMapping("/login")
    public String loginImpl(Model model, HttpSession session, String id, String pwd)  {
        CustDTO custDTO = null;
        String center = "loginfail";
        try {
            custDTO = custService.get(id);
            if (custDTO == null || custDTO.getId() == null) {
                center = "loginfail";
            } else {
                if (custDTO.getPwd().equals(pwd)) {
                    session.setAttribute("custDTO", custDTO);
                    center = "loginok";
                }
            }
        } catch (Exception e) {
            center = "loginfail";
        }
        model.addAttribute("center", center);

        return "main";
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
        return "main";
    }
    @GetMapping("/register")
    public String registerGET(CustDTO custDTO, Model model) {
        model.addAttribute("center", "register");
        model.addAttribute("custDTO", custDTO);
        return "main";
    }
    @PostMapping("/registerimpl")
    public String registerPOST(CustDTO custDTO, Model model) {
        System.out.println("custDTO = " + custDTO);
        String center = "";
        try {
            custService.register(custDTO);
            center = "registerok";
        } catch (Exception e) {
            center = "registerfail";
        }
        model.addAttribute("custDTO", custDTO);
        model.addAttribute("center", center);
        return "main";
    }
    @PostMapping("/check-duplicate-id")
    public String checkDuplicateId(
            Model model,
            String id) throws Exception {
        System.out.println("checkDuplicateId() / id = " + id);
        CustDTO custDTO1 = new CustDTO();
        List<CustDTO> custDTOs = custService.get();
        model.addAttribute("custDTO", custDTO1);
        for (CustDTO custDTO : custDTOs) {
            if (custDTO.getId().equals(id)) {
                System.out.println("이미 존재하는 id입니다.");
                model.addAttribute("status", 409);
                model.addAttribute("msg", "이미 존재하는 id입니다.");
                model.addAttribute("id", id);
                model.addAttribute("style", "color: red; font-weight: bold;");
                model.addAttribute("center", "register");
                return "main";
            }
        }

        System.out.println("사용가능한 id입니다.");
        model.addAttribute("status", 201);
        model.addAttribute("msg", "사용가능한 id입니다.");
        model.addAttribute("id", id);
        model.addAttribute("style", "color: green; font-weight: bold;");
        model.addAttribute("center", "register");
        /**
         * register-success.html 을 부르자
         * id=custDTO.getId(), msg="사용가능한 id입니다."
         * */
        return "main";
    }
    @PostMapping("/cfrimpl")
    public String cfrimpl(Model model, String imgname) throws IOException, ParseException {
        String result = CFRUtil.getText(imgname);
        Map<String, String> faceInfoMap = CFRUtil.getFaceInfoFromResult(result);
        FaceInfoDTO faceInfoDTO = new FaceInfoDTO(faceInfoMap);
        System.out.println("faceInfoDTO = " + faceInfoDTO);
        model.addAttribute("center", "cfrimpl");
        model.addAttribute("faceInfoDTO", faceInfoDTO);
        model.addAttribute("imgname", imgname);
        return "main";
    }

    @RequestMapping("/test")
    public String test(Model model, String id) {
        System.out.println("test() / id = " + id);
        model.addAttribute("status", 201);
        model.addAttribute("msg", "사용가능한 id입니다.");
        model.addAttribute("id", id);
        model.addAttribute("center", "register");
        return "main";
    }
}
