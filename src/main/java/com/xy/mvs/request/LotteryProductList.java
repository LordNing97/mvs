package com.xy.mvs.request;

import com.xy.mvs.model.LotteryProduct;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import java.util.List;

/**
 * @Author 陈璇
 * @Date 2021/1/10 17:30
 * @Version 1.0
 **/
@Getter
@Builder
@ToString
public class LotteryProductList {

    List<LotteryProduct> lotteryProductList;
    int total;
    int page;

}
