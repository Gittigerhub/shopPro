package com.example.shoppro.entity;

import com.example.shoppro.constant.ItemSellStatus;
import com.example.shoppro.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "item")
public class Item extends BaseEntity {

    @Id
    @Column(name = "item_id")           // 테이블에서 매핑될 컬럼
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                    // 상품코드

    @Column(nullable = false, length = 50)
    private String itemNm;              // 상품명

    @Column(name = "price", nullable = false)
    private int price;                          // 가격

    @Column(nullable = false)
    private int stockNumber;                    // 재고수량

    @Lob                                        // 텍스트 많이
    @Column(nullable = false)
    private String itemDetail;                  // 상품 상세설명


    @Enumerated(EnumType.STRING)                // enum 가지고 많듬 YES/NO, SELL/SOLD_OUT
    private ItemSellStatus itemSellStatus;      // 상품 판매 상태 / enum

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id")
//    private Member member;

    // 나는 하나 자식은 많음
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL) // cascade = CascadeType.ALL => Fk쪽에서 모든걸 관여한다는 뜻
//    @JoinColumn(name = "item_id")   // 사실상은 여기는 이걸 적을필요 없음, FK쪽이 주도권 있음
    private List<ItemImg> itemImgList;

}
