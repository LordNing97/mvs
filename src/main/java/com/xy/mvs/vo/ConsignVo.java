package com.xy.mvs.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @Author 陈璇
 * @Date 2021/1/14 16:21
 * @Version 1.0
 */
@Data
@ToString
@ApiModel(value = "发货参数对象")
public class ConsignVo {

    @ApiModelProperty(name = "orderId", value = "订单ID", required = true, dataType = "string")
    private String orderId;
    @ApiModelProperty(name = "logisticsCompany", value = "物流公司", required = true, dataType = "string")
    private String logisticsCompany;
    @ApiModelProperty(name = "waybillNumber", value = "运单号码", required = true, dataType = "string")
    private String waybillNumber;

}
