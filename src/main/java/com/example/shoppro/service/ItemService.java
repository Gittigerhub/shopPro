package com.example.shoppro.service;

import com.example.shoppro.DTO.ItemDTO;
import com.example.shoppro.entity.Item;
import com.example.shoppro.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;

    private final ModelMapper modelMapper;

    private final ItemImgService itemImgService;

    // 상품등록
    public Long saveItem(ItemDTO itemDTO, List<MultipartFile> multipartFile) throws IOException {

        // 아이템 DTO를 엔티티로 변환
        Item item = modelMapper.map(itemDTO, Item.class);

        // 저장
        item =
            itemRepository.save(item);

        // 이미지등록 추가
        itemImgService.saveImg(item.getId(), multipartFile);


        return item.getId();

    }

    public ItemDTO read(Long id) {

        Item item =
            itemRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        ItemDTO itemDTO = modelMapper.map(item, ItemDTO.class)
                .setItemImgDTOList(item.getItemImgList());

        return itemDTO;
    }


}
