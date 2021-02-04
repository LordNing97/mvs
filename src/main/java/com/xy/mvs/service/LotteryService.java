package com.xy.mvs.service;

import com.xy.mvs.mapper.*;
import com.xy.mvs.model.Customer;
import com.xy.mvs.model.CustomerLottery;
import com.xy.mvs.model.Lottery;
import com.xy.mvs.model.LotteryProduct;
import com.xy.mvs.request.LotteryList;
import com.xy.mvs.util.IntegerUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Resource
    private TelephoneMapper telephoneMapper;

    @Resource
    private CustomerMapper customerMapper;

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
                                      String startTime, String endTime,
                                      Integer page, Integer size){
        List<Lottery> lotteryList = lotteryMapper.getLotteryList(productName, status, startTime,
                endTime, (page - 1) * size, size);
        for(int i = 0;i < lotteryList.size();i++){
            Lottery lottery = lotteryList.get(i);
            Integer peopleNum = customerLotteryMapper.count(lottery.getId());
            lottery.setPeopleNum(peopleNum);
            Integer sumPoints = customerLotteryMapper.sumPoints(lottery.getId());
            if(sumPoints == null){
                sumPoints = 0;
            }
            LotteryProduct lotteryProduct = lotteryProductMapper.getById(lottery.getLotteryProductId());
            lottery.setProgress(String.valueOf(Double.parseDouble(sumPoints.toString()) / lotteryProduct.getPrice() * 100));
        }
        return LotteryList.builder()
                .lotteryList(lotteryList)
                .total(lotteryMapper.count(productName, status, startTime, endTime))
                .page(page)
                .build();
    }

    /**
     * 修改进度
     * @param lotteryId
     * @param num
     * @return
     */
    @Transactional
    public void modificationProgress(Integer lotteryId, Integer num){
        List<String> telephoneList = telephoneMapper.getNumber();
//        List<Customer> customerList = new ArrayList<>();
//        List<CustomerLottery> customerLotteryList = new ArrayList<>();
        int telephoneSize = telephoneList.size() - 1;
        do{
            if(telephoneSize < 0){
                telephoneSize = telephoneList.size() - 1;
            }
            //保存机器人客户
            Customer customer = new Customer();
            customer.setTelephone(telephoneList.get(telephoneSize) + IntegerUtils.telephoneNum());
            customer.setCode(IntegerUtils.random());
            customer.setIsMail(1);
            customer.setIsLottery(1);
            customer.setIsAuto(1);
            customer.setIsDel(0);
            customerMapper.saveCustomer(customer);
            //保存机器人客户抽奖
            CustomerLottery customerLottery = new CustomerLottery();
            customerLottery.setUserId(customer.getId());
            customerLottery.setLotteryId(lotteryId);
            customerLottery.setPoints(1);
            customerLottery.setIsWin(0);
            customerLottery.setIsDesignated(0);
            customerLottery.setCreateTime(LocalDateTime.now());
            customerLotteryMapper.saveCustomerLottery(customerLottery);
            telephoneSize--;
            num--;
        }while (num != 0);
//        //保存机器人客户
//        customerMapper.saveBatchCustomer(customerList);
//        for(int i = 0;i < customerList.size();i++){
//            CustomerLottery customerLottery = new CustomerLottery();
//            customerLottery.setUserId(customerList.get(i).getId());
//            customerLottery.setLotteryId(lotteryId);
//            customerLottery.setCreateTime(LocalDateTime.now());
//            customerLotteryList.add(customerLottery);
//        }
//        //保存机器人客户抽奖
//        return customerLotteryMapper.saveBatchCustomerLottery(customerLotteryList) > 0;
    }

}
