package com.shop.dto;

import com.shop.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemSearchDto {

    private String searchDateType;  //①

    private ItemSellStatus searchSellStatus;  //②

    private String searchBy;  //③

    private String searchQuery = "";  //④

}
