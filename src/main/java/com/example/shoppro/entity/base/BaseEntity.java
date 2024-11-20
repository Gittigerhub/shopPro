package com.example.shoppro.entity.base;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@EntityListeners(value = {AbstractMethodError.class})
@MappedSuperclass
@Getter
@Setter
public class BaseEntity {

    // 만든이
    @Column(updatable = false)
    @CreatedBy      // String 타입이다
    private String createBy;

    // 수정한 이
    @LastModifiedBy
    private String modifiedBy;

    // 만든날짜
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime regTime;

    // 수정날짜
    @LastModifiedDate
    private LocalDateTime updateTime;


}
