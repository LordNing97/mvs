package com.xy.mvs.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import java.time.LocalDateTime;

@Data
@ToString
@ApiModel(value = "保存购物车参数对象")
public class CarTable {

    @ApiModelProperty(name = "id", value = "id(编辑/删除时使用)", dataType = "int")
    private Integer id;
    @ApiModelProperty(name = "userId", value = "用户id", required = true, dataType = "int")
    private Integer userId;
    @ApiModelProperty(name = "mailProductId", value = "商城产品id", required = true, dataType = "int")
    private Integer mailProductId;
    @ApiModelProperty(name = "productTypeId", value = "产品类型id", required = true, dataType = "int")
    private Integer productTypeId;
    @ApiModelProperty(name = "num", value = "产品数量", required = true, dataType = "int")
    private Integer num;
    @ApiModelProperty(name = "createTime", value = "创建时间", dataType = "date")
    private LocalDateTime createTime;
    @ApiModelProperty(name = "isDel", value = "是否删除(0:未删除 1:已删除)", dataType = "int")
    private Integer isDel;

}
