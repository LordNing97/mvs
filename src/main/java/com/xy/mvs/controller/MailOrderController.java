package com.xy.mvs.controller;

import com.xy.mvs.api.Result;
import com.xy.mvs.api.ResultCode;
import com.xy.mvs.request.OrderExcel;
import com.xy.mvs.service.MailOrderService;
import com.xy.mvs.util.ExcelUtil;
import com.xy.mvs.vo.ConfirmCarTableVo;
import com.xy.mvs.vo.ConsignVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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

    @ApiOperation(value = "下单", httpMethod = "POST")
    @PostMapping("placeAnOrder")
    public Result placeAnOrder(@ModelAttribute ConfirmCarTableVo confirmCarTableVo){
        return mailOrderService.saveMailOrder(confirmCarTableVo);
    }

    @ApiOperation(value = "付款", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "订单ID", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "payment", value = "实付金额", dataType = "double", paramType = "query"),
            @ApiImplicitParam(name = "cutMoney", value = "节省金额", dataType = "double", paramType = "query"),
            @ApiImplicitParam(name = "freight", value = "运费", dataType = "double", paramType = "query")
    })
    @PostMapping("payment")
    public Result payment(Integer id, Double payment, Double cutMoney, Double freight){
        if(mailOrderService.payment(id, payment, cutMoney, freight)){
            return Result.builder().build();
        }
        return Result.builder(ResultCode.OPERATION_ERROR).build();
    }

    @ApiOperation(value = "发货", httpMethod = "POST")
    @PostMapping("consign")
    public Result consign(@ModelAttribute ConsignVo consignVo){
        if(mailOrderService.consign(consignVo)){
            return Result.builder().build();
        }
        return Result.builder(ResultCode.OPERATION_ERROR).build();
    }

    @ApiOperation(value = "交易完成(收货)", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "订单ID",dataType = "string", paramType = "query"),
    })
    @PostMapping("end")
    public Result end(Integer id){
        if(mailOrderService.end(id)){
            return Result.builder().build();
        }
        return Result.builder(ResultCode.OPERATION_ERROR).build();
    }


    @ApiOperation(value = "根据用户ID和状态获取订单", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID",dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "订单状态 (0:待付款 1:待发货 3:待收货 5:已完成 8:退款)",dataType = "string", paramType = "query"),
    })
    @GetMapping("getByUserIdAndStatus")
    public Result getByUserIdAndStatus(Integer customerId, Integer status){
        return Result.builder()
                .data(mailOrderService.getByUserIdAndStatus(customerId, status))
                .build();
    }

    @ApiOperation(value = "根据ID获取订单详细信息", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "订单ID",dataType = "string", paramType = "query"),
    })
    @GetMapping("getOrderDetails")
    public Result getOrderDetails(Integer id){
        return Result.builder()
                .data(mailOrderService.getOrderDetails(id))
                .build();
    }

    @ApiOperation(value = "分页获取订单", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status", value = "订单状态 (0:待付款 1:待发货 3:待收货 5:已完成 8:退款)",dataType = "string", paramType = "query"),
    })
    @GetMapping("getOrderByStatus")
    public Result getOrderByStatus(Integer status, Integer page, Integer size){
        return Result.builder()
                .data(mailOrderService.getOrderByStatus(status, page, size))
                .build();
    }

    @ApiOperation(value = "导出excel", httpMethod = "GET")
    @GetMapping("exportExcel")
    public void exportExcel(HttpServletResponse response) throws Exception {
        List<OrderExcel> excelData = mailOrderService.listOrderExcel();
        ExcelUtil.exportExcel(response, excelData, "待发货订单","待发货订单",15);
    }

}
