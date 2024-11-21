package com.example.shoppro.DTO;

import com.example.shoppro.constant.ItemSellStatus;
import com.example.shoppro.entity.ItemImg;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDTO {

    private Long id;

    @NotBlank
    @Size(min = 2, max = 50, message = "상품명은 2~5-자 입니다.")
    private String itemNm;

    @NotNull
    @PositiveOrZero   // 양수와 0만 가능함
    private int price;

    @NotNull
    @PositiveOrZero   // 양수와 0만 가능함
    private int stockNumber;

    @NotBlank
    private String itemDetail;

    private ItemSellStatus itemSellStatus;

    private List<ItemImgDTO> itemImgDTOList;

    public ItemDTO setItemImgDTOList(List<ItemImg> itemImgList) {

        ModelMapper modelMapper = new ModelMapper();

        List<ItemImgDTO> itemImgDTOS =
            itemImgList.stream()
                .map(itemImg -> modelMapper.map(itemImg, ItemImgDTO.class))
                    .collect(Collectors.toList());

        this.itemImgDTOList = itemImgDTOS;

        return this;

    }

    // NotBlank는  날짜에서 사용불가능

}
