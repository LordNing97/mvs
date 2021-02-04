package com.xy.mvs.controller;

import com.xy.mvs.api.Result;
import com.xy.mvs.api.ResultCode;
import com.xy.mvs.model.Lottery;
import com.xy.mvs.service.LotteryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @Author 陈璇
 * @Date 2021/1/22 16:52
 * @Version 1.0
 */
@RestController
@RequestMapping("lottery")
@Validated
@Api(tags = "抽奖接口", produces = "application/json")
public class LotteryController {

    @Resource
    private LotteryService lotteryService;

    @ApiOperation(value = "保存抽奖", httpMethod = "POST")
    @PostMapping("saveLottery")
    public Result saveLottery(Lottery lottery){
        if(lotteryService.saveLottery(lottery)){
            return Result.builder().build();
        }
        return Result.builder(ResultCode.OPERATION_ERROR).build();
    }

    @ApiOperation(value = "分页获取抽奖", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productName", value = "产品名称",dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "状态(0:全部 1:未开奖 2:进行中 3:已结束)",dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "startTime", value = "开始时间",dataType = "date", paramType = "query"),
            @ApiImplicitParam(name = "endTime", value = "结束时间",dataType = "date", paramType = "query"),
            @ApiImplicitParam(name = "page", value = "当前页",dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "size", value = "每页显示的数量",dataType = "int", paramType = "query", required = true)
    })
    @GetMapping("getLotteryList")
    public Result getLotteryList(String productName, Integer status,
                                 LocalDateTime startTime, LocalDateTime endTime,
                                 Integer page, Integer size){
        return Result.builder()
                .data(lotteryService.getLotteryList(productName, status, startTime, endTime, page, size))
                .build();
    }

    @ApiOperation(value = "修改进度", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lotteryId", value = "抽奖id",dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "num", value = "添加人数",dataType = "int", paramType = "query", required = true)
    })
    @PostMapping("modificationProgress")
    public Result modificationProgress(Integer lotteryId, Integer num){
        lotteryService.modificationProgress(lotteryId, num);
        return Result.builder().build();
    }

}
