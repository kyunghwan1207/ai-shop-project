package com.shop.dto;

import lombok.*;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class FaceInfoDTO {
    private String age;
    private String pose;
    private String emotion;
    private String gender;

    public FaceInfoDTO(Map<String, String> faceInfoMap) {
        this.age = faceInfoMap.get("age");
        this.pose = faceInfoMap.get("pose");
        this.emotion = faceInfoMap.get("emotion");
        this.gender = faceInfoMap.get("gender");
    }
}
