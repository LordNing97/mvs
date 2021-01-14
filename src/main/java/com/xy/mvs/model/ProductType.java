package com.xy.mvs.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel(value = "保存产品类型参数对象")
public class ProductType {

    @ApiModelProperty(name = "id", value = "id(编辑/删除时使用)", dataType = "int")
    private Integer id;
    @ApiModelProperty(name = "name", value = "名称", required = true, dataType = "string")
    private String name;
    @ApiModelProperty(name = "type", value = "类型(0:商城 1:抽奖)", required = true, dataType = "int")
    private Integer type;
    @ApiModelProperty(name = "isDel", value = "是否删除(0:未删除 1:已删除)", dataType = "int")
    private Integer isDel;

}
