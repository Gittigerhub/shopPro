package com.example.shoppro.service;

import com.example.shoppro.DTO.ItemDTO;
import com.example.shoppro.DTO.PageRequestDTO;
import com.example.shoppro.DTO.PageResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Test
    public void listTest() {
        // 이메일, PageRequestDTO 필요함
        String email = "sin@a.a";
        PageRequestDTO pageRequestDTO =
                new PageRequestDTO();

        pageRequestDTO.setSearchDateType("6m");
        PageResponseDTO<ItemDTO> itemDTOPageResponseDTO =
            itemService.list(pageRequestDTO, email);

        log.info(itemDTOPageResponseDTO);

        if(itemDTOPageResponseDTO
                .getDtoList() == null){
            System.out.println("널");
            System.out.println("널");
            System.out.println("널");
            System.out.println("널");
            System.out.println("널");
            System.out.println("널");
            System.out.println("널");
        }else {
            itemDTOPageResponseDTO
                    .getDtoList()
                    .forEach(itemDTO -> log.info(itemDTO));
        }



    }



}