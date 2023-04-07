package com.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopDTO {
    private String id;
    private String name;
    private String address;
    private String imgname;
    private Date rdate;
    private String ownername;
    private String phonenumber;

    // MenuDTO와 조인 용
    private int menu_id;
    private String menu_name;
    private Date menu_rdate;
    private int menu_price;
    private String menu_imgname;
}
