package com.example.shoppro.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString(exclude = "item") // 불러오는 변수명 넣어야함 // 더이상 불러오지 않겠다
@NoArgsConstructor
@Table(name = "item_img")
public class ItemImg{

    @Id
    @Column(name = "item_img_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imgName;     // 이미지 파일명

    private String oriImgName;  // 원본 이미지명

    private String imgUrl;      // 이미지 조회 경로

    private String repimgYn;    // 대표이미지 여부

    // 나는 하나 위는 많이
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;


}
