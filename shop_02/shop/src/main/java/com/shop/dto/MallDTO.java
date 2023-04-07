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
public class MallDTO {
    private int id;
    private String name;
    private String address;
    private String imgname;
    private LocalDateTime rdate;
    private String ownername;
    private String phonenumber;

    private MultipartFile img;

    public MallDTO(int id, String name, String address, String imgname, LocalDateTime rdate, String ownername, String phonenumber) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.imgname = imgname;
        this.rdate = rdate;
        this.ownername = ownername;
        this.phonenumber = phonenumber;
    }
}
