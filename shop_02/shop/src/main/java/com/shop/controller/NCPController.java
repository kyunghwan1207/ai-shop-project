package com.shop.controller;

import com.shop.dto.NCPDTO;
import com.shop.util.*;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class NCPController {

    @GetMapping("/ocr")
    public String ocr(Model model) {
        model.addAttribute("center", "ocr");
        List<Map<String, String>> arr = new ArrayList<>();
        Map<String, String> el = new HashMap<>();
        el.put("age", "10");
        Map<String, String> map = arr.get(0);// != {"age": "10"}
        System.out.println("map = " + map);

        return "main";
    }

    @PostMapping("/ocrimpl")
    public String ocrimpl(Model model, NCPDTO ncpDTO) throws IOException, ParseException {

        MultipartFile img = ncpDTO.getImg();
        String imgfilename = img.getOriginalFilename();
        FileUploadUtil.saveFile(img);

        String result = OCRUtil.getText(imgfilename);

        Map<String, String> companyInfoMap = OCRUtil.getCompanyInfo(result);

        model.addAttribute("imgname", imgfilename);
        model.addAttribute("name", companyInfoMap.get("name"));
        model.addAttribute("num", companyInfoMap.get("num"));
        model.addAttribute("center", "ocrimpl");

        return "main";
    }

    @GetMapping("/ocrcelebrity")
    public String ocrcelebrity(Model model) {
        model.addAttribute("center", "ocrcelebrity");
        return "main";
    }

    @PostMapping("/ocrcelebrityimpl")
    public String ocrcelebrityimpl(Model model, NCPDTO ncpDTO) throws IOException, ParseException {

        MultipartFile img = ncpDTO.getImg();
        String imgfilename = img.getOriginalFilename();
        FileUploadUtil.saveFile(img);

        String result = CFRCUtil.getText(imgfilename);

        Map<String, String> celInfoMap = CFRCUtil.getCelNameFromResult(result);

        model.addAttribute("imgname", imgfilename);
        model.addAttribute("name", celInfoMap.get("celName"));
        model.addAttribute("center", "ocrcelebrityimpl");

        return "main";
    }

    @GetMapping("/ocrfacerecognition")
    public String ocrFaceRecognition(Model model) {
        model.addAttribute("center", "ocrfacerecognition");
        return "main";
    }
    @PostMapping("/ocrfacerecognitionimpl")
    public String ocrFaceRecognitoinImpl(Model model, NCPDTO ncpDTO) throws IOException, ParseException {

        MultipartFile img = ncpDTO.getImg();
        String imgfilename = img.getOriginalFilename();
        FileUploadUtil.saveFile(img);

        String result = CFRUtil.getText(imgfilename);

        Map<String, String> humanInfoMap = CFRUtil.getFaceInfoFromResult(result);

        model.addAttribute("imgname", imgfilename);
        model.addAttribute("resultDTO", humanInfoMap);
        model.addAttribute("center", "ocrfacerecognitionimpl");
        return "main";
    }
}
