package com.shop.repository;

import com.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item>, ItemRepositoryCustom  {
    List<Item> findByItemNm(String itemNm);  //①
    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);  //①
    List<Item> findByPriceLessThan(Integer price); //①
    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);
    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")//①
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);  //②
    @Query(value="select * from item i where i.item_detail like %:itemDetail% order by i.price desc", nativeQuery = true) //①
    List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail);

}
   