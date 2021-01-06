package com.xy.mvs.controller;

import com.xy.mvs.api.Result;
import com.xy.mvs.api.ResultCode;
import com.xy.mvs.model.ProductType;
import com.xy.mvs.service.ProductTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * @Author 陈璇
 * @Date 2021/1/4 17:37
 * @Version 1.0
 */
@RestController
@RequestMapping("productType")
@Validated
@Api(tags = "产品类型接口", produces = "application/json")
public class ProductTypeController {

    @Resource
    private ProductTypeService productTypeService;

    @ApiOperation(value = "保存产品类型", httpMethod = "POST")
    @PostMapping("saveProductType")
    public Result saveProductType(@ModelAttribute ProductType productType){
        if(productTypeService.saveProductType(productType)){
            return Result.builder().build();
        }
        return Result.builder(ResultCode.OPERATION_ERROR).build();
    }

    @ApiOperation(value = "分页获取产品类型", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称",dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "page", value = "当前页",dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "size", value = "每页显示的数量",dataType = "int", paramType = "query", required = true)
    })
    @GetMapping("getProductTypeList")
    public Result getProductTypeList(String name, Integer type, Integer page, Integer size){
        return Result.builder()
                .data(productTypeService.getProductTypeList(name, type, page, size))
                .build();
    }

    @ApiOperation(value = "根据id获取产品类型", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id",dataType = "string", paramType = "query", required = true)
    })
    @GetMapping("getById")
    public Result getById(String id){
        return Result.builder()
                .data(productTypeService.getById(id))
                .build();
    }

    @ApiOperation(value = "修改产品类型", httpMethod = "POST")
    @PostMapping("modifyProductType")
    public Result modifyProductType(ProductType productType){
        if(productTypeService.modifyProductType(productType)){
            return Result.builder().build();
        }
        return Result.builder(ResultCode.OPERATION_ERROR).build();
    }

    @ApiOperation(value = "删除产品类型", httpMethod = "POST")
    @PostMapping("deleteProductType")
    public Result deleteProductType(String id){
        if(productTypeService.deleteProductType(id)){
            return Result.builder().build();
        }
        return Result.builder(ResultCode.OPERATION_ERROR).build();
    }

}
