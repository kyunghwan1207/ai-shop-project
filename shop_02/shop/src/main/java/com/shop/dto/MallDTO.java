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
public class MallDTO {
    private int id;
    private String name;
    private String address;
    private String imgname;
    private LocalDateTime rdate;
    private String ownername;
    private String phonenumber;

}
