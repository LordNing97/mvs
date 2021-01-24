package com.xy.mvs.service;

import com.xy.mvs.mapper.CustomerLotteryMapper;
import com.xy.mvs.mapper.LotteryMapper;
import com.xy.mvs.mapper.LotteryProductMapper;
import com.xy.mvs.model.Lottery;
import com.xy.mvs.model.LotteryProduct;
import com.xy.mvs.request.LotteryList;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author 陈璇
 * @Date 2021/1/22 16:52
 * @Version 1.0
 */
@Service
public class LotteryService {

    @Resource
    private LotteryMapper lotteryMapper;

    @Resource
    private CustomerLotteryMapper customerLotteryMapper;

    @Resource
    private LotteryProductMapper lotteryProductMapper;

    /**
     * 保存抽奖
     * @param lottery
     * @return
     */
    public boolean saveLottery(Lottery lottery){
        lottery.setCreateTime(LocalDateTime.now());
        lottery.setIsDel(0);
        return lotteryMapper.saveLottery(lottery) > 0;
    }

    /**
     * 分页获取抽奖
     * @param productName
     * @param status
     * @param startTime
     * @param endTime
     * @param page
     * @param size
     * @return
     */
    public LotteryList getLotteryList(String productName, Integer status,
                                      LocalDateTime startTime, LocalDateTime endTime,
                                      Integer page, Integer size){
        List<Lottery> lotteryList = lotteryMapper.getLotteryList(productName, status, startTime,
                endTime, (page - 1) * size, size);
        for(int i = 0;i < lotteryList.size();i++){
            Lottery lottery = lotteryList.get(i);
            Integer peopleNum = customerLotteryMapper.count(lottery.getId());
            lottery.setPeopleNum(peopleNum);
            Integer sumPoints = customerLotteryMapper.sumPoints(lottery.getId());
            LotteryProduct lotteryProduct = lotteryProductMapper.getById(lottery.getLotteryProductId());
            lottery.setProgress(String.valueOf(Double.parseDouble(sumPoints.toString()) / lotteryProduct.getPrice() * 100));
        }
        return LotteryList.builder()
                .lotteryList(lotteryList)
                .total(lotteryMapper.count(productName, status, startTime, endTime))
                .page(page)
                .build();
    }

}
