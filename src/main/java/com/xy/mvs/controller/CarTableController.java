package com.xy.mvs.controller;

import com.xy.mvs.api.Result;
import com.xy.mvs.api.ResultCode;
import com.xy.mvs.service.CarTableService;
import com.xy.mvs.vo.CarTableVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author 陈璇
 * @Date 2021/1/14 11:29
 * @Version 1.0
 */
@RestController
@RequestMapping("app/carTable")
@Validated
@Api(tags = "购物车接口", produces = "application/json")
public class CarTableController {

    @Resource
    private CarTableService carTableService;

    @ApiOperation(value = "保存购物车", httpMethod = "POST")
    @PostMapping("saveCarTable")
    public Result saveCarTable(@ModelAttribute CarTableVo carTableVo){
        if(carTableService.saveCarTable(carTableVo)){
            return Result.builder().build();
        }
        return Result.builder(ResultCode.OPERATION_ERROR).build();
    }

    @ApiOperation(value = "添加数量", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "购物车ID",dataType = "string", paramType = "query", required = true)
    })
    @PostMapping("addNum")
    public Result addNum(Integer id){
        if(carTableService.addNum(id)){
            return Result.builder().build();
        }
        return Result.builder(ResultCode.OPERATION_ERROR).build();
    }

    @ApiOperation(value = "减少数量", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "购物车ID",dataType = "string", paramType = "query", required = true)
    })
    @PostMapping("cutNum")
    public Result cutNum(Integer id){
        if(carTableService.cutNum(id)){
            return Result.builder().build();
        }
        return Result.builder(ResultCode.OPERATION_ERROR).build();
    }

    @ApiOperation(value = "删除购物车", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "购物车ID",dataType = "string", paramType = "query", required = true)
    })
    @PostMapping("deleteCarTable")
    public Result deleteCarTable(Integer id){
        if(carTableService.deleteCarTable(id)){
            return Result.builder().build();
        }
        return Result.builder(ResultCode.OPERATION_ERROR).build();
    }

    @ApiOperation(value = "分页获取产品", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID",dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = "page", value = "当前页",dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "size", value = "每页显示的数量",dataType = "int", paramType = "query", required = true)
    })
    @GetMapping("getCarTableAndProductList")
    public Result getCarTableAndProductList(Integer userId, Integer page, Integer size){
        return Result.builder()
                .data(carTableService.getCarTableAndProductList(userId, page, size))
                .build();
    }

}
