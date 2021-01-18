package com.xy.mvs.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import java.time.LocalDateTime;

@Data
@ToString
@ApiModel(value = "保存抽奖订单参数对象")
public class LotteryOrder {

    @ApiModelProperty(name = "id", value = "id(编辑/删除时使用)", dataType = "int")
    private Integer id;
    @ApiModelProperty(name = "customerId", value = "客户id", required = true, dataType = "int")
    private Integer customerId;
    @ApiModelProperty(name = "addressId", value = "地址id", required = true, dataType = "int")
    private Integer addressId;
    @ApiModelProperty(name = "orderNumber", value = "订单编号", required = true, dataType = "string")
    private String orderNumber;
    @ApiModelProperty(name = "status", value = "订单状态(2:待发货 3:待收货 4:已完成)", required = true, dataType = "int")
    private Integer status;
    @ApiModelProperty(name = "createTime", value = "创建时间", dataType = "date")
    private LocalDateTime createTime;
    @ApiModelProperty(name = "consignTime", value = "发货时间", required = true, dataType = "date")
    private LocalDateTime consignTime;
    @ApiModelProperty(name = "endTime", value = "交易完成时间", required = true, dataType = "date")
    private LocalDateTime endTime;
    @ApiModelProperty(name = "isDel", value = "是否删除(0:未删除 1:已删除)",  dataType = "int")
    private Integer isDel;

}
