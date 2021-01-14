package com.xy.mvs.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import java.util.List;

/**
 * @Author 陈璇
 * @Date 2021/1/14 11:55
 * @Version 1.0
 */
@Getter
@Builder
@ToString
public class CarTableAndProductList {

    List<CarTableAndProduct> carTableAndProductList;
    int total;
    int page;

}
