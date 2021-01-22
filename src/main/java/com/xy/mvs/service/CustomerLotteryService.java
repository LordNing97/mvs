package com.xy.mvs.service;

import com.xy.mvs.mapper.CustomerLotteryMapper;
import com.xy.mvs.model.CustomerLottery;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @Author 陈璇
 * @Date 2021/1/22 16:30
 * @Version 1.0
 */
@Service
public class CustomerLotteryService {

    @Resource
    private CustomerLotteryMapper customerLotteryMapper;

    /**
     * 保存
     * @param customerLottery
     * @return
     */
    public boolean saveCustomerLottery(CustomerLottery customerLottery){
        customerLottery.setCreateTime(LocalDateTime.now());
        return customerLotteryMapper.saveCustomerLottery(customerLottery) > 0;
    }

}
