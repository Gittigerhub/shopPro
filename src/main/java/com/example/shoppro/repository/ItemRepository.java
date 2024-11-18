package com.example.shoppro.repository;

import com.example.shoppro.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    // 제품명으로 검색, 제품명은 동일한 이름이 있을 수 있으니
    // 여러개 출력가능한 List 사용한다.
    public List<Item> findByItemNm (String itemNm);

    // 위 함수를 아래 쿼리로 같게 작성함
    @Query("select i from Item i where i.itemNm = :itemNm")
    public List<Item> selcetwhereItemNm (String itemNm);




    // 제품명으로 검색, 비슷하면 맞게끔
    public List<Item> findByItemNmContaining (String itemNm);

    // 위 함수를 아래 쿼리로 같게 작성함
    @Query("select i from Item i where i.itemNm like concat('%',:itemNm,'%')")
    public List<Item> selectWItemNmLike (String itemNm);



    // https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
    // jpa쿼리 사용법 상세설명 나와있음

    // 상세설명으로 검색
    public List<Item> findByItemDetailContaining (String itemDetail);

    // 가격으로 검색
    public List<Item> findByPriceLessThan (Integer price);

    // 정렬조건 까지 추가 (내림차순)
    List<Item> findByPriceLessThanOrderByPriceDesc (Integer price);


}
