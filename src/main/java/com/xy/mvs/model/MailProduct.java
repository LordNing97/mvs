package com.xy.mvs.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel(value = "保存商城产品参数对象")
public class MailProduct {

    @ApiModelProperty(name = "id", value = "id(编辑/删除时使用)", dataType = "int")
    private Integer id;
    @ApiModelProperty(name = "productTypeId", value = "产品类型ID", required = true, dataType = "int")
    private Integer productTypeId;
    @ApiModelProperty(name = "name", value = "名称", required = true, dataType = "string")
    private String name;
    @ApiModelProperty(name = "photo", value = "图片", required = true, dataType = "string")
    private String photo;
    @ApiModelProperty(name = "information", value = "详情", required = true, dataType = "string")
    private String information;
    @ApiModelProperty(name = "price", value = "价格", required = true, dataType = "double")
    private double price;
    @ApiModelProperty(name = "num", value = "数量", required = true, dataType = "int")
    private Integer num;
    @ApiModelProperty(name = "status", value = "状态(0:上架 1:下架)", required = true, dataType = "int")
    private Integer status;
    @ApiModelProperty(name = "isDel", value = "是否删除(0:未删除 1:已删除)", dataType = "int")
    private Integer isDel;

    private String productTypeName;

}
