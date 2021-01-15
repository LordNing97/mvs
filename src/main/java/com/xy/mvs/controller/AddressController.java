package com.xy.mvs.controller;

import com.xy.mvs.api.Result;
import com.xy.mvs.api.ResultCode;
import com.xy.mvs.model.Address;
import com.xy.mvs.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * @Author 陈璇
 * @Date 2021/1/15 14:51
 * @Version 1.0
 */
@RestController
@RequestMapping("app/address")
@Validated
@Api(tags = "地址接口", produces = "application/json")
public class AddressController {

    @Resource
    private AddressService addressService;

    @ApiOperation(value = "保存地址", httpMethod = "POST")
    @PostMapping("saveAddress")
    public Result saveAddress(@ModelAttribute Address address){
        if(addressService.saveAddress(address)){
            return Result.builder().build();
        }
        return Result.builder(ResultCode.OPERATION_ERROR).build();
    }

    @ApiOperation(value = "根据ID获取地址", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "地址ID",dataType = "string", paramType = "query", required = true)
    })
    @GetMapping("getById")
    public Result getById(Integer id){
        return Result.builder()
                .data(addressService.getById(id))
                .build();
    }

    @ApiOperation(value = "获取用户地址", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID",dataType = "string", paramType = "query", required = true)
    })
    @GetMapping("getAddressList")
    public Result getAddressList(Integer userId){
        return Result.builder()
                .data(addressService.getAddressList(userId))
                .build();
    }

    @ApiOperation(value = "编辑地址", httpMethod = "POST")
    @PostMapping("modifyAddress")
    public Result modifyAddress(@ModelAttribute Address address){
        if(addressService.modifyAddress(address)){
            return Result.builder().build();
        }
        return Result.builder(ResultCode.OPERATION_ERROR).build();
    }

    @ApiOperation(value = "删除地址", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "地址ID",dataType = "string", paramType = "query", required = true)
    })
    @PostMapping("deleteAddress")
    public Result deleteAddress(Integer id){
        if(addressService.deleteAddress(id)){
            return Result.builder().build();
        }
        return Result.builder(ResultCode.OPERATION_ERROR).build();
    }

}
