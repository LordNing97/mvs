package com.xy.mvs.controller;

import com.xy.mvs.api.Result;
import com.xy.mvs.api.ResultCode;
import com.xy.mvs.model.MailOrder;
import com.xy.mvs.service.MailOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @Author 陈璇
 * @Date 2021/1/12 11:18
 * @Version 1.0
 */
@RestController
@RequestMapping("mailOrder")
@Validated
@Api(tags = "商城订单接口", produces = "application/json")
public class MailOrderController {

    @Resource
    private MailOrderService mailOrderService;

    @ApiOperation(value = "保存商城订单", httpMethod = "POST")
    @PostMapping("saveMailOrder")
    public Result saveMailOrder(MailOrder mailOrder){
        if(mailOrderService.saveMailOrder(mailOrder)){
            return Result.builder().build();
        }
        return Result.builder(ResultCode.OPERATION_ERROR).build();
    }

}
