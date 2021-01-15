package com.xy.mvs.request;

import com.xy.mvs.model.Address;
import com.xy.mvs.model.OrderItem;
import com.xy.mvs.model.ShippingInformation;
import lombok.Data;
import lombok.ToString;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author 陈璇
 * @Date 2021/1/15 11:34
 * @Version 1.0
 */
@Data
@ToString
public class OrderAndItemInfoList {

    private Integer id;
    private Integer customerId;
    private Integer addressId;
    private double payment;
    private double totalPrice;
    private Integer totalNum;
    private double memberCut;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime paymentTime;
    private LocalDateTime consignTime;
    private LocalDateTime endTime;

    private Address address;

    //订单详细信息
    private List<OrderItem> orderItem;

    private String name;
    private String orderNumber;

    //运送信息
    private ShippingInformation shippingInformation;

}
