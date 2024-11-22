package com.example.shoppro.service;

import com.example.shoppro.entity.Item;
import com.example.shoppro.entity.ItemImg;
import com.example.shoppro.repository.ItemImgRepository;
import com.example.shoppro.repository.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class ItemImgService {

    private final ItemRepository itemRepository;

    private final FileService fileService;

    private final ItemImgRepository itemImgRepository;

    // 이미지 등록
    public void saveImg(Long id, List<MultipartFile> multipartFile) throws IOException {
        // 이미지 등록은 어디에 무엇을 할것인가?
        // 이미지는 아이템꺼
        // 그러므로 아이템 PK, 이미지 파일이 필요함, 이미지파일을 경로를 잘라서
        // 경로와 함께 이름을 저장한다.

        log.info("아이템이미지서비스로 들어온 id" + id);
        if (multipartFile != null) {
            for (MultipartFile img : multipartFile) {
                if (!img.isEmpty()) {
                    log.info("아이템이미지서비스로 들어온 이미지" + img.getOriginalFilename());

                    // DB저장 Local 저장 두개가 필요함
                    // Local 저장, 물리적인 저장    >   하기위해 FileService를 만듬
                    String savedFileName =              // UUID가 포함된 물리적인 파일이름
                        fileService.uploadFile(img);    // 시그니처 추가 필요

                    // DB저장
                    // 엔티티를 가져왔다면 중복코드를 사용 할 필요가 없어진다. 해볼것
                    // 엔티티 repository를 의존성 주입
                    Item item =
                            itemRepository.findById(id).get();

                    String imgUrl = "/images/item/" + savedFileName;

                    ItemImg itemImg = new ItemImg();
                    itemImg.setItem(item);                              // 본문, 이미지가 달릴 아이템
                    itemImg.setImgName(savedFileName);                  // UUID포함 저장될 이름
                    itemImg.setImgUrl(imgUrl);                          // 경로
                    itemImg.setOriImgName(img.getOriginalFilename());   // 원래이름

                    // 대표이미지 여부 확인
                    if (multipartFile.indexOf(img) == 0) {
                        itemImg.setRepimgYn("Y");                       // 대표이미지
                    } else {
                        itemImg.setRepimgYn("N");                       // 대표이미지
                    }

                    itemImgRepository.save(itemImg);

                }
            }
        }

    }


}
