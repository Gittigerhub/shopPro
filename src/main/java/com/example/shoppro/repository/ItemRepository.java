package com.example.shoppro.repository;

import com.example.shoppro.entity.Item;
import com.example.shoppro.repository.search.ItemsearchRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemsearchRepository {

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
    public List<Item> findByPriceGreaterThan (Integer price);
    public List<Item> findByPriceGreaterThanOrderByPriceAsc (Integer price);
    public List<Item> findByPriceGreaterThanEqual (Pageable pageable, Integer price);

    // 정렬조건 까지 추가 (내림차순)
    List<Item> findByPriceLessThanOrderByPriceDesc (Integer price);

    // nativeQuery 사용
                                            // 데이터 베이스엔 자동으로 _가 붙어서 들어가기 때문에 데이터 베이스에
                                            // 적힌 방식인 i.item_Nm 으로 쿼리문을 작성해줘야 정상작동한다.
    @Query(value = "select * from Item i where i.item_Nm = :itemNm", nativeQuery = true)
    List<Item> nativeQuerySelectWhereNameLike(String itemNm, Pageable pageable);


}
