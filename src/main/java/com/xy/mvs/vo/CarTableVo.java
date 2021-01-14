package com.xy.mvs.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @Author 陈璇
 * @Date 2021/1/14 11:56
 * @Version 1.0
 */
@Data
@ToString
@ApiModel(value = "保存购物车信息参数对象")
public class CarTableVo {

    @ApiModelProperty(name = "userId", value = "用户ID", dataType = "int")
    private Integer userId;
    @ApiModelProperty(name = "mailProductId", value = "产品ID", dataType = "int")
    private Integer mailProductId;
    @ApiModelProperty(name = "productTypeId", value = "产品类型ID", dataType = "int")
    private Integer productTypeId;
    @ApiModelProperty(name = "num", value = "数量", dataType = "int")
    private Integer num;

}
