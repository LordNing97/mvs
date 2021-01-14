package com.xy.mvs.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @Author 陈璇
 * @Date 2021/1/14 13:44
 * @Version 1.0
 */
@Data
@ToString
@ApiModel(value = "确认购物车参数对象")
public class ConfirmCarTableVo {

    @ApiModelProperty(name = "carTableIds", value = "购物车IDS", dataType = "string")
    private String carTableIds;
    @ApiModelProperty(name = "mailProductId", value = "商城产品id(直接下单时使用)", dataType = "int")
    private Integer mailProductId;
    @ApiModelProperty(name = "productTypeId", value = "产品类型ID(直接下单时使用)", dataType = "int")
    private Integer productTypeId;
    @ApiModelProperty(name = "addressId", value = "地址ID", dataType = "int", required = true)
    private Integer addressId;
    @ApiModelProperty(name = "userId", value = "用户ID", required = true, dataType = "int")
    private Integer userId;
    @ApiModelProperty(name = "num", value = "购买数量(直接下单时使用)", required = true, dataType = "int")
    private Integer num;
    @ApiModelProperty(name = "totalPrice", value = "总金额", required = true, dataType = "double")
    private double totalPrice;

    @ApiModelProperty(name = "payment", value = "应付金额", required = true, dataType = "double")
    private double payment;
    @ApiModelProperty(name = "totalNum", value = "商品总数量", required = true, dataType = "int")
    private Integer totalNum;
    @ApiModelProperty(name = "memberCut", value = "会员优惠", required = true, dataType = "double")
    private double memberCut;


}
