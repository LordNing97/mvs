package com.xy.mvs.controller;

import com.xy.mvs.api.Result;
import com.xy.mvs.api.ResultCode;
import com.xy.mvs.model.CustomerLottery;
import com.xy.mvs.service.CustomerLotteryService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
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

    @ApiOperation(value = "根据抽奖Id获取所有积分", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lotteryId", value = "抽奖id",dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "page", value = "当前页",dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "size", value = "每页显示的数量",dataType = "int", paramType = "query", required = true)
    })
    @GetMapping("getCustomerLotteryList")
    public Result getCustomerLotteryList(Integer lotteryId, Integer page, Integer size){
        return Result.builder()
                .data(customerLotteryService.getCustomerLotteryList(lotteryId, page, size))
                .build();
    }

    @ApiOperation(value = "修改内定", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id",dataType = "int", paramType = "query, required = true"),
            @ApiImplicitParam(name = "isDesignated", value = ", required = true",dataType = "int", paramType = "query", required = true)
    })
    @PostMapping("modifyIsDesignated")
    public Result modifyIsDesignated(Integer id, Integer isDesignated){
        if(customerLotteryService.modifyIsDesignated(id, isDesignated)){
            return Result.builder().build();
        }
        return Result.builder(ResultCode.OPERATION_ERROR).build();
    }

}
