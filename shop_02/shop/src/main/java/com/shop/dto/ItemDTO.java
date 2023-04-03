package com.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemDTO {
    private int id;
    private String name;
    private int price;
    private String imgname;
    private LocalDateTime rdate;
    private MultipartFile img;

    public ItemDTO(String name, int price, String imgname) {
        this.name = name;
        this.price = price;
        this.imgname = imgname;
    }

    public ItemDTO(int id, String name, int price, String imgname, LocalDateTime rdate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imgname = imgname;
        this.rdate = rdate;
    }
}
