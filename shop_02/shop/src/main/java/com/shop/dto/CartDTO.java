package com.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartDTO {
    private int id;
    private String cust_id;
    private int item_id;
    private int cnt;
    private Date rdate;

    private String item_name;
    private String cust_name;
    private int item_price;
    private String item_imgname;

    public CartDTO(int id, String cust_id, int item_id, int cnt, Date rdate) {
        this.id = id;
        this.cust_id = cust_id;
        this.item_id = item_id;
        this.cnt = cnt;
        this.rdate = rdate;
    }
}
