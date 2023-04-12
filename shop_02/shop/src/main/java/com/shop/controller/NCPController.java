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
import java.util.Map;

@Controller
public class NCPController {

    @GetMapping("/ocr")
    public String ocr(Model model) {
        model.addAttribute("center", "ncp/ocr");
        return "main";
    }

    /**
     * 사업자 등록증 문서 정보 추출 탬플릿
     * */
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
        model.addAttribute("center", "ncp/ocrimpl");

        return "main";
    }

    @GetMapping("/celebrityfacerecognition")
    public String celebrityFaceRecognition(Model model) {
        model.addAttribute("center", "ncp/celebrityfacerecognition");
        return "main";
    }

    @PostMapping("/celebrityfacerecognitionimpl")
    public String celebrityFaceRecognitionImpl(Model model, NCPDTO ncpDTO) throws IOException, ParseException {

        MultipartFile img = ncpDTO.getImg();
        String imgfilename = img.getOriginalFilename();
        FileUploadUtil.saveFile(img);

        String result = CFRCUtil.getText(imgfilename);
        Map<String, String> celInfoMap = CFRCUtil.getCelNameAndConfidenceFromResult(result);
        model.addAttribute("imgname", imgfilename);
        model.addAttribute("name", celInfoMap.get("celName"));
        model.addAttribute("confidence", celInfoMap.get("confidence"));
        model.addAttribute("center", "ncp/celebrityfacerecognitionimpl");

        return "main";
    }

    @GetMapping("/facerecognition")
    public String faceRecognition(Model model) {
        model.addAttribute("center", "ncp/facerecognition");
        return "main";
    }
    @PostMapping("/facerecognitionimpl")
    public String faceRecognitoinImpl(Model model, NCPDTO ncpDTO) throws IOException, ParseException {

        MultipartFile img = ncpDTO.getImg();
        String imgfilename = img.getOriginalFilename();
        FileUploadUtil.saveFile(img);

        String result = CFRUtil.getText(imgfilename);
        Map<String, String> humanInfoMap = CFRUtil.getFaceInfoFromResult(result);
        System.out.println("humanInfoMap = " + humanInfoMap);
        model.addAttribute("imgname", imgfilename);
        model.addAttribute("resultDTO", humanInfoMap);
        model.addAttribute("center", "ncp/facerecognitionimpl");
        return "main";
    }
}
