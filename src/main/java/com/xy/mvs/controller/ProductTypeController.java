package com.xy.mvs.controller;

import com.xy.mvs.api.Result;
import com.xy.mvs.api.ResultCode;
import com.xy.mvs.model.ProductType;
import com.xy.mvs.service.ProductTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}
