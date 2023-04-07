package com.shop.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class MsgDTO {
    private String sendid;
    private String receiveid;
    private String content1;
    private String content2;
}
