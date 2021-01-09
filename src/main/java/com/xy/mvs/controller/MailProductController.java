package com.xy.mvs.controller;

import com.xy.mvs.api.Result;
import com.xy.mvs.api.ResultCode;
import com.xy.mvs.model.MailProduct;
import com.xy.mvs.service.MailProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author 陈璇
 * @Date 2021/1/9 14:00
 * @Version 1.0
 */
@RestController
@RequestMapping("mailProduct")
@Validated
@Api(tags = "商城产品接口", produces = "application/json")
public class MailProductController {

    @Resource
    private MailProductService mailProductService;

    @ApiOperation(value = "保存商城产品", httpMethod = "POST")
    @PostMapping("saveMailProduct")
    public Result saveMailProduct(@ModelAttribute MailProduct mailProduct){
        if(mailProductService.saveMailProduct(mailProduct)){
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
                .data(mailProductService.getById(id))
                .build();
    }

    @ApiOperation(value = "修改商城产品", httpMethod = "POST")
    @PostMapping("modifyMailProduct")
    public Result modifyMailProduct(@ModelAttribute MailProduct mailProduct){
        if(mailProductService.modifyMailProduct(mailProduct)){
            return Result.builder().build();
        }
        return Result.builder(ResultCode.OPERATION_ERROR).build();
    }

    @ApiOperation(value = "分页获取商城产品", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称",dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "productTypeId", value = "产品类型id",dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "状态(-1:全部 0:上架 1:下架)",dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "page", value = "当前页",dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "size", value = "每页显示的数量",dataType = "int", paramType = "query", required = true)
    })
    @GetMapping("getMailProductList")
    public Result getMailProductList(String name, Integer productTypeId, Integer status, Integer page, Integer size){
        return Result.builder()
                .data(mailProductService.getMailProductList(name, productTypeId, status, page, size))
                .build();
    }

    @ApiOperation(value = "删除商城产品", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id",dataType = "string", paramType = "query", required = true)
    })
    @PostMapping("deleteMailProduct")
    public Result deleteMailProduct(Integer id){
        if(mailProductService.deleteMailProduct(id)){
            return Result.builder().build();
        }
        return Result.builder(ResultCode.OPERATION_ERROR).build();
    }

}
