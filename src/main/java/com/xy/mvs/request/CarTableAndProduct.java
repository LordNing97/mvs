package com.xy.mvs.request;

import lombok.Data;
import lombok.ToString;

/**
 * @Author 陈璇
 * @Date 2021/1/14 11:33
 * @Version 1.0
 */
@Data
@ToString
public class CarTableAndProduct {

    //购物车
    private String id;
    private String mailProductId;
    private String productTypeId;
    private String productTypeNum;
    private Integer num;

    //产品
    private String introduce;
    private Double price;
    private String picture;

    //产品类型
    private String productTypeContent;
    private Double productTypeDiscount;
    private Double productTypeMembershipPrice;

    private boolean isCheck = true;

}
