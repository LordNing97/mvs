package com.xy.mvs.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;
import java.time.LocalDateTime;

@Data
@ToString
@ApiModel(value = "保存运送信息参数对象")
public class ShippingInformation {

  private Integer id;
  private Integer orderId;
  private String logisticsCompany;
  private String waybillNumber;
  private LocalDateTime createTime;
  private Integer isDel;

}
