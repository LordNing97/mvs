package com.xy.mvs.mapper;

import com.xy.mvs.model.CustomerLottery;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author 陈璇
 * @Date 2021/1/22 16:28
 * @Version 1.0
 */
@Mapper
public interface CustomerLotteryMapper {

    /**
     * 保存
     * @param customerLottery
     * @return
     */
    int saveCustomerLottery(CustomerLottery customerLottery);

}
