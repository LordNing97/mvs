package com.xy.mvs.request;

import lombok.Data;
import lombok.ToString;
import java.time.LocalDateTime;

/**
 * @Author 陈璇
 * @Date 2021/1/15 14:20
 * @Version 1.0
 */
@Data
@ToString
public class OrderExcel {

    private String orderNumber;
    private double payment;
    private double totalPrice;
    private LocalDateTime createTime;
    private LocalDateTime paymentTime;

    private String productName;
    private Integer productNum;
    private double productPrice;
    private double productTotalPrice;

    private String name;
    private String telephone;
    private String address;

}
