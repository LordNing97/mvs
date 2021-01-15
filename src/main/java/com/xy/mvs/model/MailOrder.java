package com.xy.mvs.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import java.time.LocalDateTime;

@Data
@ToString
@ApiModel(value = "保存商城订单参数对象")
public class MailOrder {

    @ApiModelProperty(name = "id", value = "id(编辑/删除时使用)", dataType = "int")
    private Integer id;
    @ApiModelProperty(name = "customerId", value = "客户id", required = true, dataType = "int")
    private Integer customerId;
    @ApiModelProperty(name = "addressId", value = "收货地址id", required = true, dataType = "int")
    private Integer addressId;
    @ApiModelProperty(name = "orderNumber", value = "订单编号", required = true, dataType = "string")
    private String orderNumber;
    @ApiModelProperty(name = "payment", value = "实付金额", required = true, dataType = "double")
    private double payment;
    @ApiModelProperty(name = "totalPrice", value = "总金额", required = true, dataType = "double")
    private double totalPrice;
    @ApiModelProperty(name = "totalNum", value = "商品总数量", required = true, dataType = "int")
    private Integer totalNum;
    @ApiModelProperty(name = "memberCut", value = "会员优惠", required = true, dataType = "double")
    private double memberCut;
    @ApiModelProperty(name = "status", value = "订单状态(0:待付款 1:已付款 2:待发货 3:待收货 4:已完成)", required = true, dataType = "int")
    private Integer status;
    @ApiModelProperty(name = "createTime", value = "创建时间", dataType = "date")
    private LocalDateTime createTime;
    @ApiModelProperty(name = "paymentTime", value = "付款时间", dataType = "date")
    private LocalDateTime paymentTime;
    @ApiModelProperty(name = "consignTime", value = "发货时间", dataType = "date")
    private LocalDateTime consignTime;
    @ApiModelProperty(name = "endTime", value = "完成时间", dataType = "date")
    private LocalDateTime endTime;
    @ApiModelProperty(name = "isDel", value = "是否删除(0:未删除 1:已删除)", dataType = "int")
    private Integer isDel;

}
