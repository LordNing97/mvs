package com.xy.mvs.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * @Author 陈璇
 * @Date 2021/1/15 13:57
 * @Version 1.0
 */
@Getter
@Builder
@ToString
public class OrderList {

    List<OrderAndItemInfoList> orderList;
    int total;
    int page;

}
