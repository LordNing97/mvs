package com.xy.mvs.controller;

import com.xy.mvs.api.Result;
import com.xy.mvs.api.ResultCode;
import com.xy.mvs.model.CustomerLottery;
import com.xy.mvs.service.CustomerLotteryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import javax.annotation.Resource;

/**
 * @Author 陈璇
 * @Date 2021/1/22 16:33
 * @Version 1.0
 */
public class CustomerLotteryController {

    @Resource
    private CustomerLotteryService customerLotteryService;

    @ApiOperation(value = "参与抽奖", httpMethod = "POST")
    @PostMapping("saveCustomerLottery")
    public Result saveCustomerLottery(CustomerLottery customerLottery){
        if(customerLotteryService.saveCustomerLottery(customerLottery)){
            return Result.builder().build();
        }
        return Result.builder(ResultCode.OPERATION_ERROR).build();
    }

}
