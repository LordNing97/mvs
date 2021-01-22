package com.xy.mvs.mapper;

import com.xy.mvs.model.Lottery;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author 陈璇
 * @Date 2021/1/22 15:07
 * @Version 1.0
 */
@Mapper
public interface LotteryMapper {

    /**
     * 保存抽奖
     * @param lottery
     * @return
     */
    int saveLottery(Lottery lottery);

    int count();

}
