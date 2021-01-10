package com.xy.mvs.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel(value = "保存客户参数对象")
public class Customer {

    @ApiModelProperty(name = "id", value = "id(编辑/删除时使用)", dataType = "int")
    private Integer id;
    @ApiModelProperty(name = "name", value = "姓名", required = true, dataType = "string")
    private String name;
    @ApiModelProperty(name = "telephone", value = "手机号", required = true, dataType = "string")
    private String telephone;
    @ApiModelProperty(name = "turnover", value = "成交量", required = true, dataType = "int")
    private Integer turnover;
    @ApiModelProperty(name = "consumptionAmount", value = "消费金额", required = true, dataType = "double")
    private double consumptionAmount;
    @ApiModelProperty(name = "numberWinners", value = "中奖量", required = true, dataType = "int")
    private Integer numberWinners;
    @ApiModelProperty(name = "rechargePoints", value = "充值积分", required = true, dataType = "int")
    private Integer rechargePoints;
    @ApiModelProperty(name = "consumptionPoints", value = "消费积分", required = true, dataType = "int")
    private Integer consumptionPoints;
    @ApiModelProperty(name = "residualIntegral", value = "剩余积分", required = true, dataType = "int")
    private Integer residualIntegral;
    @ApiModelProperty(name = "code", value = "邀请码", required = true, dataType = "string")
    private String code;
    @ApiModelProperty(name = "type", value = "类型(0:商城 1:抽奖)", required = true, dataType = "int")
    private Integer type;
    @ApiModelProperty(name = "isDel", value = "是否删除(0:未删除 1:已删除)", dataType = "int")
    private Integer isDel;

}
