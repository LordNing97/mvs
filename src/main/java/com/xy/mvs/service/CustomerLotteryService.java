package com.xy.mvs.service;

import com.xy.mvs.mapper.CustomerLotteryMapper;
import com.xy.mvs.model.CustomerLottery;
import com.xy.mvs.request.CustomerLotteryList;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

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

    /**
     * 根据抽奖Id获取所有积分
     * @param lotteryId
     * @param page
     * @param size
     * @return
     */
    public CustomerLotteryList getCustomerLotteryList(Integer lotteryId, Integer page, Integer size){
        List<CustomerLottery> customerLotteryList = customerLotteryMapper.getCustomerLotteryList(lotteryId, (page - 1) * size, size);
        return CustomerLotteryList.builder()
                .customerLotteryList(customerLotteryList)
                .total(customerLotteryMapper.count(lotteryId))
                .page(page)
                .build();
    }

}
