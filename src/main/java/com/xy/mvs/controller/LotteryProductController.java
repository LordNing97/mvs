package com.xy.mvs.controller;

import com.xy.mvs.api.Result;
import com.xy.mvs.api.ResultCode;
import com.xy.mvs.model.LotteryProduct;
import com.xy.mvs.service.LotteryProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * @Author 陈璇
 * @Date 2021/1/10 17:18
 * @Version 1.0
 **/
@RestController
@RequestMapping("lotteryProduct")
@Validated
@Api(tags = "抽奖产品接口", produces = "application/json")
public class LotteryProductController {

    @Resource
    private LotteryProductService lotteryProductService;

    @ApiOperation(value = "保存抽奖产品", httpMethod = "POST")
    @PostMapping("saveLotteryProduct")
    public Result saveLotteryProduct(@ModelAttribute LotteryProduct lotteryProduct){
        if(lotteryProductService.saveLotteryProduct(lotteryProduct)){
            return Result.builder().build();
        }
        return Result.builder(ResultCode.OPERATION_ERROR).build();
    }

    @ApiOperation(value = "根据id获取", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id",dataType = "string", paramType = "query", required = true)
    })
    @GetMapping("getById")
    public Result getById(Integer id){
        return Result.builder()
                .data(lotteryProductService.getById(id))
                .build();
    }

    @ApiOperation(value = "修改抽奖产品", httpMethod = "POST")
    @PostMapping("modifyLotteryProduct")
    public Result modifyLotteryProduct(@ModelAttribute LotteryProduct lotteryProduct){
        if(lotteryProductService.modifyLotteryProduct(lotteryProduct)){
            return Result.builder().build();
        }
        return Result.builder(ResultCode.OPERATION_ERROR).build();
    }

    @ApiOperation(value = "分页获取抽奖产品", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称",dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "productTypeId", value = "产品类型id",dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "状态(-1:全部 0:上架 1:下架)",dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "page", value = "当前页",dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "size", value = "每页显示的数量",dataType = "int", paramType = "query", required = true)
    })
    @GetMapping("getLotteryProductList")
    public Result getLotteryProductList(String name, Integer productTypeId, Integer status, Integer page, Integer size){
        return Result.builder()
                .data(lotteryProductService.getLotteryProductList(name, productTypeId, status, page, size))
                .build();
    }

    @ApiOperation(value = "删除抽奖产品", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id",dataType = "string", paramType = "query", required = true)
    })
    @PostMapping("deleteLotteryProduct")
    public Result deleteLotteryProduct(Integer id){
        if(lotteryProductService.deleteLotteryProduct(id)){
            return Result.builder().build();
        }
        return Result.builder(ResultCode.OPERATION_ERROR).build();
    }

}
