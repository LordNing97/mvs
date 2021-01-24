package com.xy.mvs.request;

import com.xy.mvs.model.Lottery;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import java.util.List;

/**
 * @Author 陈璇
 * @Date 2021/1/24 20:09
 * @Version 1.0
 **/
@Getter
@Builder
@ToString
public class LotteryList {

    List<Lottery> lotteryList;
    int total;
    int page;

}
