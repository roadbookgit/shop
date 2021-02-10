package com.shop.entity;

import com.shop.constant.ItemSellStatus;
import com.shop.dto.ItemFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import com.shop.exception.OutOfStockException;

@Entity  //①
@Table(name="item")  //①
@Getter
@Setter
@ToString
public class Item extends BaseEntity{

    @Id  //②
    @Column(name="item_id")  //②
    @GeneratedValue(strategy = GenerationType.AUTO)  //②
    private Long id;       //상품 코드

    @Column(nullable = false, length = 50)  //③
    private String itemNm; //상품명

    @Column(name="price", nullable = false)
    private int price; //가격

    @Column(nullable = false)
    private int stockNumber; //재고수량

    @Lob
    @Column(nullable = false)
    private String itemDetail; //상품 상세 설명

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus; //상품 판매 상태

    public void updateItem(ItemFormDto itemFormDto){
        this.itemNm = itemFormDto.getItemNm();
        this.price = itemFormDto.getPrice();
        this.stockNumber = itemFormDto.getStockNumber();
        this.itemDetail = itemFormDto.getItemDetail();
        this.itemSellStatus = itemFormDto.getItemSellStatus();
    }

    public void removeStock(int stockNumber){
        int restStock = this.stockNumber - stockNumber;  //①
        if(restStock<0){
            throw new OutOfStockException("상품의 재고가 부족 합니다. (현재 재고 수량: " + this.stockNumber + ")");  //②
        }
        this.stockNumber = restStock;  //③
    }

    public void addStock(int stockNumber){  //①
        this.stockNumber += stockNumber;
    }


}