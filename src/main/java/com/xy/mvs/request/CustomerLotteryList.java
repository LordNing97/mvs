package com.xy.mvs.request;

import com.xy.mvs.model.CustomerLottery;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import java.util.List;

/**
 * @Author 陈璇
 * @Date 2021/1/24 20:35
 * @Version 1.0
 **/
@Getter
@Builder
@ToString
public class CustomerLotteryList {

    List<CustomerLottery> customerLotteryList;
    int total;
    int page;

}
