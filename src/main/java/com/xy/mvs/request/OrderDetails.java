package com.xy.mvs.request;

import com.xy.mvs.model.*;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @Author 陈璇
 * @Date 2021/1/15 13:53
 * @Version 1.0
 */
@Data
@ToString
@Builder
public class OrderDetails {

    //订单信息
    private MailOrder mailOrder;

    private LotteryOrder lotteryOrder;

    //订单详细信息
    private List<OrderItem> orderItem;

    //运送信息
    private ShippingInformation shippingInformation;

    //收货地址
    private Address address;

}
