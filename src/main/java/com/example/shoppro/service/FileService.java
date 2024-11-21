package com.example.shoppro.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileService {

    // 물리적인 사진저장 혹은 읽기등을 하려면
    // 사진파일과 그로 만들어진 내용들이 필요하다

    // 저장 경로
    @Value("${itemImgLocation}")
    String itemImgLocation;

    public String uploadFile(MultipartFile multipartFile) throws IOException {

        UUID uuid = UUID.randomUUID();  // 서로 다른개체를 구별하기 위해
        // 이름을 부여할 때 사용, 실제 사용시 중복될 일이 거의 없기 때문에

        // 본래 이름에서 원하는 부분만 자름
        String extension = multipartFile.getOriginalFilename()
                .substring(multipartFile.getOriginalFilename().lastIndexOf("."));
                    // .jpg등 "."을 기준으으로 확장자명만 남기고 잘라버림

        //  완성된 저장용 물리적인 파일 이름
        String savedFileName = uuid.toString()+extension;
                                    // ardq3q312@#!$!aa.jpg

        // 경로
        String fileUploadFullUrl = itemImgLocation + "/" + savedFileName;

        // 물리적인 저장
        // 다른 방법으로는 >> multipartFile.transferTo(file);
        FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);
        // FileOutputStream에 마우스 가져다 대고 시그니처 생성 진행
        // 하면 클래스 옆에 throws FileNotFoundException 이 생성됨

        fos.write(multipartFile.getBytes());
        // 이것도 getBytesdml 시그니처 생성하면 throws IOException 생김
        // throws IOException가 throws FileNotFoundException보다 상위라 변경됨

        fos.close();

        return savedFileName;

    }

}
