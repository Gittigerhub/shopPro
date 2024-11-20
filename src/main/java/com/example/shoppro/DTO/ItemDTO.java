package com.example.shoppro.DTO;

import com.example.shoppro.constant.ItemSellStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;

import java.time.LocalDateTime;

public class ItemDTO {

    private Long id;

    private String itemNm;

    private int price;

    private int stockNumber;

    private String itemDetail;

    private ItemSellStatus itemSellStatus;

    private LocalDateTime regTime;

    private LocalDateTime updateTime;

}
