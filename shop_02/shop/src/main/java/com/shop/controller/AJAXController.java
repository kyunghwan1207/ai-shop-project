package com.shop.controller;

import com.shop.dto.CustDTO;
import com.shop.service.CustService;
import com.shop.util.CFRUtil;
import com.shop.util.FileUploadUtil;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@RestController
public class AJAXController {
    @PostMapping("/saveimg")
    public String saveimg(Model model, MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        FileUploadUtil.saveFile(file);
        return filename;
    }
}
