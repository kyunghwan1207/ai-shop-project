package com.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuDTO {
    private int id;
    private String shop_id;
    private String name;
    private int price;
    private String imgname;
    private Date rdate;
}
