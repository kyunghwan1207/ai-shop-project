package com.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

    public ItemDTO(String name, int price, String imgname) {
        this.name = name;
        this.price = price;
        this.imgname = imgname;
    }
}
