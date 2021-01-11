package com.xy.mvs.controller;

import com.xy.mvs.api.Result;
import com.xy.mvs.api.ResultCode;
import com.xy.mvs.model.Customer;
import com.xy.mvs.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author 陈璇
 * @Date 2021/1/10 18:01
 * @Version 1.0
 **/
@RestController
@RequestMapping("customer")
@Validated
@Api(tags = "客户接口", produces = "application/json")
public class CustomerController {

    @Resource
    private CustomerService customerService;

    @ApiOperation(value = "保存客户", httpMethod = "POST")
    @PostMapping("saveCustomer")
    public Result saveCustomer(@ModelAttribute Customer customer){
        if(customerService.saveCustomer(customer)){
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
                .data(customerService.getById(id))
                .build();
    }

    @ApiOperation(value = "分页获取客户", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称",dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "类型(-1:全部 0:商城 1:抽奖)",dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "page", value = "当前页",dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "size", value = "每页显示的数量",dataType = "int", paramType = "query", required = true)
    })
    @GetMapping("getCustomerList")
    public Result getCustomerList(String name, Integer type, Integer page, Integer size){
        return Result.builder()
                .data(customerService.getCustomerList(name, type, page, size))
                .build();
    }

}
