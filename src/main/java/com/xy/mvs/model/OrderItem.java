package com.xy.mvs.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import java.time.LocalDateTime;

@Data
@ToString
@ApiModel(value = "保存订单详情参数对象")
public class OrderItem {

    @ApiModelProperty(name = "id", value = "id(编辑/删除时使用)", dataType = "int")
    private Integer id;
    @ApiModelProperty(name = "orderId", value = "订单id", required = true, dataType = "int")
    private Integer orderId;
    @ApiModelProperty(name = "mailProductId", value = "商城产品id", required = true, dataType = "int")
    private Integer mailProductId;
    @ApiModelProperty(name = "productTypeId", value = "产品类型id", required = true, dataType = "int")
    private Integer productTypeId;
    @ApiModelProperty(name = "productTypeName", value = "产品类型名称", required = true, dataType = "string")
    private String productTypeName;
    @ApiModelProperty(name = "num", value = "商品购买数量", required = true, dataType = "int")
    private Integer num;
    @ApiModelProperty(name = "title", value = "商品标题", required = true, dataType = "string")
    private String title;
    @ApiModelProperty(name = "price", value = "商品单价", required = true, dataType = "double")
    private double price;
    @ApiModelProperty(name = "totalPrice", value = "商品总金额", required = true, dataType = "double")
    private double totalPrice;
    @ApiModelProperty(name = "photo", value = "商品图片", required = true, dataType = "string")
    private String photo;
    @ApiModelProperty(name = "discount", value = "商品优惠", required = true, dataType = "double")
    private double discount;
    @ApiModelProperty(name = "createTime", value = "创建时间", dataType = "date")
    private LocalDateTime createTime;
    @ApiModelProperty(name = "isDel", value = "是否删除(0:未删除 1:已删除)", dataType = "int")
    private Integer isDel;

}
